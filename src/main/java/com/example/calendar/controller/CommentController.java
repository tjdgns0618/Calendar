package com.example.calendar.controller;


import com.example.calendar.dto.CreateCommentRequest;
import com.example.calendar.dto.CreateCommentResponse;
import com.example.calendar.service.CommentService;
import com.example.calendar.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/schedules")
public class CommentController {
    private final CommentService commentService;
    private final ScheduleService scheduleService;

    // 생성자에서 의존성 주입을 이뤄주는 어노테이션
    @Autowired
    public CommentController(CommentService commentService, ScheduleService scheduleService) {
        this.commentService = commentService;
        this.scheduleService = scheduleService;
    }

    /**
     * 고유 번호에 해당하는 일정에 댓글을 생성하는 메서드
     *
     * @param scheduleId 일정 고유 번호
     * @param request    댓글 생성 요청 DTO
     * @return 댓글 정보가 담긴 객체
     */
    @PostMapping("/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest request
    ) {
        // 해당 고유 번호에 일정이 존재하는지 검사하는 메서드
        scheduleService.checkScheduleExistenceById(scheduleId);
        // 일정에 새로운 댓글을 DB에 저장하고, 그 결과를 응답 DTO로 반환하는 메서드
        CreateCommentResponse response = commentService.saveComment(scheduleId, request);
        // DB에서 생성을 성공했음을 클라이언트에게 알려주기 위해 상태를 저장
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.CREATED);
        // 생성된 응답 DTO를 바디에 담아서 최종 객체를 만듬
        ResponseEntity<CreateCommentResponse> responseEntity = builder.body(response);

        return responseEntity;
    }
}
