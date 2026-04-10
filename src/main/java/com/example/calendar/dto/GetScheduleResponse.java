package com.example.calendar.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetScheduleResponse {

    private final Long id;
    private final String scheduleName;
    private final String scheduleContents;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetScheduleResponse(Long id, String scheduleName, String scheduleContents, String author, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.scheduleName = scheduleName;
        this.scheduleContents = scheduleContents;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
