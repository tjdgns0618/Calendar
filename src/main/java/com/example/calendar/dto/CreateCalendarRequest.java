package com.example.calendar.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCalendarRequest {
    private String calendarName;
    private String calendarContents;
    private String writerName;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
