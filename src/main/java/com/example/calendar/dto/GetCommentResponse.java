package com.example.calendar.dto;

import java.time.LocalDateTime;

public class GetCommentResponse {
    private final Long commentId;
    private final String commentContent;
    private final String author;
    private final Long scheduleId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCommentResponse(Long commentId, String commentContent, String author, Long scheduleId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.author = author;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getAuthor() {
        return author;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
