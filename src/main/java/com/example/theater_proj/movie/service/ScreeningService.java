package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.SeatsBookingStatus;
import com.example.theater_proj.movie.dto.response.RetrieveScreeningDTO;
import com.example.theater_proj.movie.dto.response.RoomScreeningDTO;
import com.example.theater_proj.movie.dto.response.SeatsDTO;
import com.example.theater_proj.movie.dto.response.SingleSreeningDTO;
import com.example.theater_proj.movie.entity.*;
import com.example.theater_proj.movie.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScreeningService {
    private JpaScreeningRepository screeningRepository;

    public ScreeningService(
            JpaScreeningRepository screeningRepository
    ) {
        this.screeningRepository = screeningRepository;
    }

    //movie, theater, date로 필터링 한 뒤 상영 정보 보여주기
    public List<RoomScreeningDTO> findScreeningsByMovieTheaterDate(int movieId, int theaterId, LocalDate date){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        //날짜 별 특정 영화 전체 조회
        List<Screening> screenings = screeningRepository.findScreeningsByCriteria(movieId, theaterId, startOfDay, endOfDay);

        Map<Room, List<Screening>> screeningGroup = screenings.stream().collect(Collectors.groupingBy(Screening::getRoom));

        return getRoomScreening(screeningGroup);
    }

    public RetrieveScreeningDTO getSeatMap (int screening_id){
        Screening screening = screeningRepository.findById(screening_id).get();

        //예약된 좌석 얻어오기
        List<Reservation> reservations = screening.getReservations();

        Room room = screening.getRoom();
        List<Seat> allSeats = room.getSeats();

        Map<Integer, SeatsBookingStatus> reservedSeats = new HashMap<>();

        for (Reservation reservation : reservations) {
            List<ReservationDetail> reservationDetails = reservation.getReservationDetails();
            for (ReservationDetail reservationDetail : reservationDetails) {
                Seat seat = reservationDetail.getSeat();
                reservedSeats.put(seat.getId(), reservation.getBookingStatus());
            }
        }

        //SeatMap 만들어서 좌석 상태 표현
        int rowCount = room.getRowCount();
        int colCount = room.getColCount();

        SeatsDTO[][] seatMap = new SeatsDTO[colCount][rowCount];

        for (Seat seat : allSeats) {
            SeatsBookingStatus seatsBookingStatus = reservedSeats.getOrDefault(seat.getId(), SeatsBookingStatus.AVAILABLE);

            seatMap[seat.getCol()][seat.getRow()] = new SeatsDTO(seat.getId(), seat.getRow(), seat.getCol(), seatsBookingStatus);
        }

        RetrieveScreeningDTO retrieveScreeningDTO = new RetrieveScreeningDTO(
                screening.getId(),
                room.getRoomGrade(),
                room.getPrice(),
                seatMap
        );

        return retrieveScreeningDTO;
    }

    private List<RoomScreeningDTO> getRoomScreening(Map<Room, List<Screening>> ScreeningGroup){
        return ScreeningGroup.entrySet().stream().map(
                entry -> {
                    Room singleRoom = entry.getKey();
                    List<SingleSreeningDTO> singleScreenigs = entry
                            .getValue()
                            .stream()
                            .map(this::screeningConvertDTO)
                            .sorted(Comparator.comparing(SingleSreeningDTO::startTime)).toList();
                    return new RoomScreeningDTO(singleRoom.getRoomNumber(), singleRoom.getRoomGrade(), singleScreenigs);
                }
        ).sorted(Comparator.comparing(RoomScreeningDTO::roomNumber)).toList();
    }

    private SingleSreeningDTO screeningConvertDTO(Screening screening){
        Room room = screening.getRoom();
        int totalSeats = room.getRowCount() * room.getColCount();
        int reservedSeats = screening.getReservations().size();
        int remainSeats = totalSeats - reservedSeats;

        return new SingleSreeningDTO(
                screening.getId(),
                screening.getStartTime(),
                screening.getEndTime(),
                remainSeats
        );
    }
}
