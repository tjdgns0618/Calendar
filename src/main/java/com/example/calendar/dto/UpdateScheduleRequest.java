package com.example.calendar.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String scheduleName;
    private String author;
    private String password;
}
