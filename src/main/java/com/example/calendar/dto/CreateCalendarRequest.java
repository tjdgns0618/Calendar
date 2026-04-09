package com.example.calendar.dto;

import lombok.Getter;

@Getter
public class CreateCalendarRequest {
    private String calendarName;
    private String calendarContents;
    private String writerName;
    private String password;
}
