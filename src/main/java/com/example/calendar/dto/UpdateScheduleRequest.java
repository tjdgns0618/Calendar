package com.example.calendar.dto;

public class UpdateScheduleRequest {
    private String scheduleName;
    private String author;
    private String password;

    public String getScheduleName() {
        return scheduleName;
    }

    public String getAuthor() {
        return author;
    }

    public String getPassword() {
        return password;
    }
}
