package com.example.calendar.dto;

public class UpdateScheduleRequest {
    private final String scheduleName;
    private final String author;
    private final String password;

    public UpdateScheduleRequest(String scheduleName, String author, String password) {
        this.scheduleName = scheduleName;
        this.author = author;
        this.password = password;
    }

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
