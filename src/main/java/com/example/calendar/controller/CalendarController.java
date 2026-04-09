package com.example.calendar.controller;


import com.example.calendar.dto.*;
import com.example.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 이름을 key로 사용하여 작성자 이름에 해당하는 모든 일정을 바디로 반환해주는 함수
     * @RequestParam에 required 속성을 변경해주어 있어도 없어도 되게 해주었습니다. 이로인해 다른 GetMapping 어노테이션이 작동이 가능해졌습니다.
     * @param writerName
     * @return 생성된 모든 캘린더 조회 응답
     */
    @GetMapping("/calendars")
    public ResponseEntity<List<GetCalendarResponse>> getAllCalendars(@RequestParam(required = false) String writerName) {
        return ResponseEntity.status(HttpStatus.OK).body(calendarService.findAllByWriterName(writerName));
    }

    /**
     * id를 경로로 해당하는 일정을 반환해주는 함수
     * @param id
     * @return 생성된 단 건 캘린더 조회 응답
     */
    @GetMapping("/calendars/{id}")
    public ResponseEntity<GetCalendarResponse> getCalendarById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(calendarService.findById(id));
    }

    @PutMapping("/calendars/{id}")
    public ResponseEntity<UpdateCalendarResponse> updateCalendarById(
            @PathVariable Long id,
            @RequestBody UpdateCalendarRequest request) {
        return  ResponseEntity.status(HttpStatus.OK).body(calendarService.update(id, request));
    }

}
