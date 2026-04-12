package com.example.calendar.dto;

import java.time.LocalDateTime;

public class CreateScheduleResponse {

    private final Long scheduleId;
    private final String scheduleName;
    private final String scheduleContents;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateScheduleResponse(Long scheduleId, String scheduleName, String scheduleContents, String author, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.scheduleContents = scheduleContents;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public String getScheduleContents() {
        return scheduleContents;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
