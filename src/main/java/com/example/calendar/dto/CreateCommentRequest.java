package com.example.calendar.dto;

public class CreateCommentRequest {
    private final String commentContent;
    private final String author;
    private final String password;

    public CreateCommentRequest(String commentContent, String author, String password) {
        this.commentContent = commentContent;
        this.author = author;
        this.password = password;
    }

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
