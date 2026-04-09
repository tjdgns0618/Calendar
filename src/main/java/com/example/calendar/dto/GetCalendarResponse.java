package com.example.calendar.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCalendarResponse {

    private final Long id;
    private final String calendarName;
    private final String calendarContents;
    private final String writerName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCalendarResponse(Long id, String calendarName, String calendarContents, String writerName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.calendarName = calendarName;
        this.calendarContents = calendarContents;
        this.writerName = writerName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
