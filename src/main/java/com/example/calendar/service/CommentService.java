package com.example.calendar.service;

import com.example.calendar.dto.CreateCommentRequest;
import com.example.calendar.dto.CreateCommentResponse;
import com.example.calendar.dto.GetCommentResponse;
import com.example.calendar.entity.Comment;
import com.example.calendar.exception.CommentLimitOverException;
import com.example.calendar.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// @Component 어노테이션이 포함되어 있어 자동으로 스프링 빈으로 등록된다.
@Service
public class CommentService {
    // 인터페이스이지만 메서드들을 불러와서 사용할수가 있다??
    // 스프링 서버가 켜질 때, 스프링 데이터 JPA가 이 인터페이스를 발견하고 그 자리에 프록시라는 가짜 구현체 클래스를 채워 넣는다.
    // 서비스가 의존성 주입을 받은 이 CommentRepository는 스프링이 실행 시점에 동적으로 만들어낸 프록시 객체가 되는거다.
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * ID에 해당하는 일정에 새로운 댓글을 생성하여 DB에 저장하는 메서드
     *
     * @param scheduleId 일정의 고유 식별 번호
     * @param request    댓글 생성 요청 DTO
     * @return DB에 정상적으로 저장된 댓글 응답 DTO
     */
    @Transactional
    public CreateCommentResponse saveComment(Long scheduleId, CreateCommentRequest request) {
        // 해당 ID에 존재하는 댓글의 갯수
        int commentCount = commentRepository.countByScheduleId(scheduleId);

        // 한 일정에 댓글 갯수가 10개라면 예외 발생
        if (commentCount == 10)
            throw new CommentLimitOverException("댓글은 10개 초과하게 적을 수 없습니다.");

        // 요청 DTO를 DB에 저장하기 위해서 순수 엔티티로 변환
        Comment comment = new Comment(request.getCommentContent(), request.getAuthor(), request.getPassword(), scheduleId);

        // 엔티티를 DB에 영속화 하고 생성된 ID가 포함된 결과를 저장
        Comment savedComment = commentRepository.save(comment);

        // ID가 포함된 응답 DTO로 객체 생성후 반환
        return new CreateCommentResponse(
                savedComment.getCommentId(),
                savedComment.getCommentContents(),
                savedComment.getAuthor(),
                savedComment.getScheduleId(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    /**
     * ID에 해당하는 일정에 존재하는 모든 댓글을 DB에 찾아주는 메서드
     *
     * @param scheduleId 일정의 고유 식별 번호
     * @return 찾은 모든 댓글 정보들의 DTO들
     */
    @Transactional(readOnly = true)
    public List<GetCommentResponse> getAllCommentsByScheduleId(Long scheduleId) {
        // DB에서 해당 일정 ID를 가진 모든 댓글들을 조회하여 순수 엔티티로 변경
        List<Comment> comments = commentRepository.findAllCommentsByScheduleId(scheduleId);
        // 응답 DTO들을 담을 리스트 생성
        List<GetCommentResponse> commentResponses = new ArrayList<>();
        // 순수 엔티티에 정보들을 DTO 객체로 생성하고 담아주기
        for (Comment comment : comments) {
            GetCommentResponse commentResponse = new GetCommentResponse(
                    comment.getCommentId(),
                    comment.getCommentContents(),
                    comment.getAuthor(),
                    // 일정 ID는 요청하는 ID를 사용해서 달라지는일이 없어지게 했다.
                    scheduleId,
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
            // DTO 리스트에 저장
            commentResponses.add(commentResponse);
        }
        // 댓글 조회 응답 DTO 리스트 반환
        return commentResponses;
    }
}
