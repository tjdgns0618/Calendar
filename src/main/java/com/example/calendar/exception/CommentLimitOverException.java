package com.example.calendar.exception;

public class CommentLimitOverException extends RuntimeException {
    public CommentLimitOverException(String message) {
        super(message);
    }
}
