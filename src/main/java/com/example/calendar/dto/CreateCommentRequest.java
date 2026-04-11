package com.example.calendar.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String commentContent;
    private String author;
    private String password;
}
