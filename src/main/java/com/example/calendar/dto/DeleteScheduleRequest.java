package com.example.calendar.dto;

public class DeleteScheduleRequest {
    private final String password;

    public DeleteScheduleRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
