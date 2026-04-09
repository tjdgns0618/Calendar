package com.example.calendar.service;

import com.example.calendar.dto.CreateCalendarRequest;
import com.example.calendar.dto.CreateCalendarResponse;
import com.example.calendar.entity.Calendar;
import com.example.calendar.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

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
                savedCalendar.getPassword(),
                savedCalendar.getCreatedAt(),
                savedCalendar.getModifiedAt()
        );
    }

}
