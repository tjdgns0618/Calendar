package com.example.calendar.controller;


import com.example.calendar.dto.CreateCommentRequest;
import com.example.calendar.dto.CreateCommentResponse;
import com.example.calendar.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/schedules")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest createCommentRequest
    ) {
        CreateCommentResponse response = commentService.saveComment(scheduleId, createCommentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    
}
