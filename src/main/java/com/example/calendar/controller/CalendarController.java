package com.example.calendar.controller;


import com.example.calendar.dto.CreateCalendarRequest;
import com.example.calendar.dto.CreateCalendarResponse;
import com.example.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    // 컨트롤러에서는 서비스와의 상호작용만 한다.
    private final CalendarService calendarService;

    /**
     * CalendarService에서 생성 로직 실행후 생성된 응답만 반환함
     * @param request
     * @return 생성된 캘린더 응답
     */
    @PostMapping("/calendars")
    public ResponseEntity<CreateCalendarResponse> createCalendar(@RequestBody CreateCalendarRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(calendarService.save(request));
    }


}
