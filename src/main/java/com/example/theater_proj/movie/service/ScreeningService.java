package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.SeatsBookingStatus;
import com.example.theater_proj.movie.dto.RoomScreeningDTO;
import com.example.theater_proj.movie.dto.SeatsDTO;
import com.example.theater_proj.movie.dto.SingleSreeningDTO;
import com.example.theater_proj.movie.entity.*;
import com.example.theater_proj.movie.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScreeningService {
    private ScreeningRepository screeningRepository;
    private ReservationRepository reservationRepository;
    private SeatRepository seatRepository;

    public ScreeningService(
            ScreeningRepository screeningRepository,
            ReservationRepository reservationRepository,
            SeatRepository seatRepository
    ) {
        this.screeningRepository = screeningRepository;
        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
    }

    //movie, theater, date로 필터링 한 뒤 상영 정보 보여주기
    public List<RoomScreeningDTO> findScreeningsByMovieTheaterDate(int movieId, int theaterId, LocalDate date){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        //날짜 별 특정 영화 전체 조회
        List<Screening> screenings = screeningRepository.findScreeningsByCriteria(movieId, theaterId, startOfDay, endOfDay);

        Map<Room, List<Screening>> ScreeningGroup = screenings.stream().collect(Collectors.groupingBy(Screening::getRoom));

        List<RoomScreeningDTO> showRoomScreening = ScreeningGroup.entrySet().stream().map(
                entry -> {
                    Room singleRoom = entry.getKey();
                    List<SingleSreeningDTO> singleScreenigs = entry.getValue().stream().map(
                            screening -> new SingleSreeningDTO(
                                    screening.getId(),
                                    screening.getScreeningTime(),
                                    (long) singleRoom.getColCount() *singleRoom.getColCount() -
                                            screening.getReservations().stream().count()
                            )
                    ).sorted(Comparator.comparing(SingleSreeningDTO::getStartTime)).toList();

                    return new RoomScreeningDTO(singleRoom.getRoomNumber(), singleRoom.getRoomGrade(), singleScreenigs);
                }
        ).sorted(Comparator.comparing(RoomScreeningDTO::getRoomNumber)).toList();

        return showRoomScreening;
    }


    public SeatsDTO[][] getSeatMap (int screening_id){
        Screening screening = screeningRepository.findScreeningById(screening_id).get();
        List<Reservation> reservations = reservationRepository.findAllByScreeningId(screening_id);

        Room room = screening.getRoom();
        List<Seat> allSeats = seatRepository.findAllByRoomId(room.getId());
        Set<Integer> reservedSeats = new HashSet<>();

        for (Reservation reservation : reservations) {
            for (Seat seat : reservation.getSeats()) {
                reservedSeats.add(seat.getId());
            }
        }

        int rowCount = room.getRowCount();
        int colCount = room.getColCount();

        SeatsDTO[][] seatMap = new SeatsDTO[colCount][rowCount];

        for (Seat seat : allSeats) {
            SeatsBookingStatus seatsBookingStatus = reservedSeats.contains(seat.getId())
                    ? SeatsBookingStatus.RESERVED : SeatsBookingStatus.AVAILABLE;

            seatMap[seat.getCol()][seat.getRow()] = new SeatsDTO(seat.getId(), seat.getRow(), seat.getCol(), seatsBookingStatus);
        }

        return seatMap;
    }
}
