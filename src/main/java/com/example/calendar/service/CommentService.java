package com.example.calendar.service;

import com.example.calendar.dto.CreateCommentRequest;
import com.example.calendar.dto.CreateCommentResponse;
import com.example.calendar.entity.Comment;
import com.example.calendar.exception.CommentLimitException;
import com.example.calendar.exception.ScheduleNotFoundException;
import com.example.calendar.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final ScheduleService scheduleService;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(ScheduleService scheduleService, CommentRepository commentRepository) {
        this.scheduleService = scheduleService;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public CreateCommentResponse saveComment(Long scheduleId, CreateCommentRequest request) {
        boolean existence = scheduleService.existsCheckById(scheduleId);

        int commentCount = commentRepository.countById(scheduleId);

        if (commentCount == 10)
            throw new CommentLimitException("댓글은 10개 초과하게 적을 수 없습니다.");

        if (!existence)
            throw new ScheduleNotFoundException("존재하지 않는 일정입니다.");

        Comment comment = new Comment(request.getCommentContent(), request.getAuthor(), request.getPassword(), scheduleId);

        Comment savedComment = commentRepository.save(comment);

        return new CreateCommentResponse(
                savedComment.getCommentId(),
                savedComment.getCommentContents(),
                savedComment.getAuthor(),
                savedComment.getId(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }


}
