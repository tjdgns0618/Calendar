package com.example.calendar.dto;

public class CreateCommentRequest {
    private String commentContent;
    private String author;
    private String password;

    public String getCommentContent() {
        return commentContent;
    }

    public String getAuthor() {
        return author;
    }

    public String getPassword() {
        return password;
    }
}
