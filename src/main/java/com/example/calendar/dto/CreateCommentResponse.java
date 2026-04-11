package com.example.calendar.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {

    private final Long commentId;
    private final String commentContent;
    private final String author;
    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    public CreateCommentResponse(Long commentId, String commentContent, String author, Long id, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.author = author;
        this.id = id;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }



}
