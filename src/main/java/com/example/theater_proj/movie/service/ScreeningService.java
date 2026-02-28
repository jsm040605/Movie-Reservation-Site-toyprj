package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.dto.response.screening.list.AllScreeningsResponse;
import com.example.theater_proj.movie.dto.response.screening.list.ScreeningInfoIcludedRoom;
import com.example.theater_proj.movie.dto.response.screening.list.ScreeningSimpleInfo;
import com.example.theater_proj.movie.model.SeatsBookingStatus;
import com.example.theater_proj.movie.dto.response.screening.retrieve.RetrieveScreeningResponse;
import com.example.theater_proj.movie.dto.response.screening.retrieve.SeatsInfo;
import com.example.theater_proj.movie.entity.*;
import com.example.theater_proj.movie.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScreeningService {
    private final JpaScreeningRepository screeningRepository;
    private final JpaReservationRepository reservationRepository;

    //movie, theater, date로 필터링 한 뒤 상영 정보 보여주기
    public AllScreeningsResponse findScreeningsByMovieTheaterDate(Long movieId, Long theaterId, LocalDate date){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        //날짜 별 특정 영화 전체 조회
        List<Screening> screenings = screeningRepository.findScreeningsByCriteria(movieId, theaterId, startOfDay, endOfDay);
        //screening을 dto로 변환하면 room_id가 사라지는데 room_id로 그룹핑 하려면 room_id가 필요함.

        List<ScreeningInfoIcludedRoom> convertedScreenings = getConvertedScreenings(screenings);

        return new AllScreeningsResponse(convertedScreenings);
    }

    private List<ScreeningInfoIcludedRoom> getConvertedScreenings(List<Screening> screenings) {
        return screenings.stream().collect(Collectors.groupingBy(Screening::getRoom)).entrySet().stream().map(
                entry -> {
                    Room room = entry.getKey();
                    List<ScreeningSimpleInfo> simpleInfo = entry.getValue().stream().map(ScreeningSimpleInfo::fromEntity).toList();

                    return new ScreeningInfoIcludedRoom(room.getRoomNumber(), room.getRoomGrade(), simpleInfo);
                }
        ).sorted(Comparator.comparing(ScreeningInfoIcludedRoom::roomNumber)).toList();
    }

    public RetrieveScreeningResponse getSeatMap (Long screening_id){
        Screening screening = screeningRepository.findByIdWithDetail(screening_id).orElseThrow(IllegalArgumentException::new);

        // SeatMap 만들어서 좌석 상태표현
        Room room = screening.getRoom();
        List<Seats> allSeats = room.getSeats();

        int rowCount = room.getRowCount();
        int colCount = room.getColCount();

        SeatsInfo[][] seatMap = new SeatsInfo[colCount][rowCount];

        for (Seats seat : allSeats) {
            SeatsBookingStatus seatsBookingStatus = screening.checkReservedSeats(seat) ?
                    SeatsBookingStatus.RESERVED :
                    SeatsBookingStatus.AVAILABLE;

            seatMap[seat.getCol()][seat.getRow()] = new SeatsInfo(seat.getId(), seat.getRow(), seat.getCol(), seatsBookingStatus);
        }

        return new RetrieveScreeningResponse(
                screening.getId(),
                room.getRoomGrade(),
                seatMap
        );
    }
}
