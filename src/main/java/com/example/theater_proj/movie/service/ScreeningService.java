package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.dto.RoomScreeningDTO;
import com.example.theater_proj.movie.dto.SingleSreeningDTO;
import com.example.theater_proj.movie.entity.Movie;
import com.example.theater_proj.movie.entity.Room;
import com.example.theater_proj.movie.entity.Screening;
import com.example.theater_proj.movie.entity.Theater;
import com.example.theater_proj.movie.repository.MovieRepository;
import com.example.theater_proj.movie.repository.RoomRepository;
import com.example.theater_proj.movie.repository.ScreeningRepository;
import com.example.theater_proj.movie.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScreeningService {
    private ScreeningRepository screeningRepository;

    public ScreeningService(
            ScreeningRepository screeningRepository
    ) {
        this.screeningRepository = screeningRepository;
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
                                    screening.getScreeningTime()
                            )
                    ).sorted(Comparator.comparing(SingleSreeningDTO::getStartTime)).toList();

                    return new RoomScreeningDTO(singleRoom.getRoomNumber(), singleRoom.getRoomGrade(), singleScreenigs);
                }
        ).toList();

        return showRoomScreening;
    }
}
