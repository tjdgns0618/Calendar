package com.example.calendar.dto;

import lombok.Getter;

@Getter
public class UpdateCalendarRequest {
    private String calendarName;
    private String writerName;
    private String password;
}
