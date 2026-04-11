package com.example.calendar.exception;

public class CommentLimitException extends RuntimeException {
    public CommentLimitException(String message) {
        super(message);
    }
}
