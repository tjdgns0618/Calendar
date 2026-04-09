package com.example.calendar.service;

import com.example.calendar.dto.*;
import com.example.calendar.entity.Calendar;
import com.example.calendar.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    /**
     * 생성 요청에 대한 생성 응답 DTO를 반환해주는 함수
     * @param request
     * @return 생성 응답 DTO
     */
    @Transactional
    public CreateCalendarResponse save(CreateCalendarRequest request) {
        Calendar calendar = new Calendar(
                request.getCalendarName(),
                request.getCalendarContents(),
                request.getWriterName(),
                request.getPassword()
        );
        Calendar savedCalendar = calendarRepository.save(calendar);
        return new CreateCalendarResponse(
                savedCalendar.getId(),
                savedCalendar.getCalendarName(),
                savedCalendar.getCalendarContents(),
                savedCalendar.getWriterName(),
                savedCalendar.getCreatedAt(),
                savedCalendar.getModifiedAt()
        );
    }

    /**
     * 이름을 Key로 받는 조회 요청에 대한 모든 응답 DTO를 반환하는 함수
     * @param writerName
     * @return Name에 해당하는 모든 일정들 DTO
     */
    @Transactional(readOnly = true)
    public List<GetCalendarResponse> findAllByWriterName(String writerName) {
        List<Calendar> calendars = calendarRepository.findAllByWriterName(writerName);
        List<GetCalendarResponse> dtos = new ArrayList<>();
        for (Calendar calendar : calendars) {
            GetCalendarResponse dto =  new GetCalendarResponse(
                    calendar.getId(),
                    calendar.getCalendarName(),
                    calendar.getCalendarContents(),
                    calendar.getWriterName(),
                    calendar.getCreatedAt(),
                    calendar.getModifiedAt()
            );
            dtos.add(dto);
        }
        Sort.by(Sort.Direction.DESC, "modifiedAt");
        return dtos;
    }

    /**
     * id에 해당하는 일정을 조회하는 함수
     * @param id
     * @return id에 해당하는 응답 DTO
     */
    @Transactional(readOnly = true)
    public GetCalendarResponse findById(Long id){
        Calendar calendar = calendarRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return new GetCalendarResponse(
                calendar.getId(),
                calendar.getCalendarName(),
                calendar.getCalendarContents(),
                calendar.getWriterName(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );
    }

    /**
     * id에 해당하는 일정의 이름과 작성자명을 변경하는 함수
     * @param id
     * @param request
     * @return 변경된 데이터 응답 DTO
     */
    @Transactional
    public UpdateCalendarResponse update(Long id, UpdateCalendarRequest request) {
        Calendar calendar = calendarRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        boolean correctPassword = calendar.getPassword().equals(request.getPassword());
        if(!correctPassword){
            throw new IllegalStateException("맞지 않는 비밀번호 입니다.");
        }

        calendar.updateCalendar(request.getCalendarName(), request.getWriterName());

        return new UpdateCalendarResponse(
                calendar.getId(),
                calendar.getCalendarName(),
                calendar.getCalendarContents(),
                calendar.getWriterName(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );
    }


}
