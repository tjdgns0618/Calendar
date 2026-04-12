package com.example.calendar.dto;

import java.time.LocalDateTime;
import java.util.List;

public class GetScheduleWithCommentsResponse {
    private final Long scheduleId;
    private final String scheduleName;
    private final String scheduleContents;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<GetCommentResponse> comments;

    public GetScheduleWithCommentsResponse(Long scheduleId, String scheduleName, String scheduleContents, String author, LocalDateTime createdAt, LocalDateTime modifiedAt, List<GetCommentResponse> comments) {
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.scheduleContents = scheduleContents;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
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

    public List<GetCommentResponse> getComments() {
        return comments;
    }
}
