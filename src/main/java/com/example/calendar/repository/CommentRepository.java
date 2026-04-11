package com.example.calendar.repository;

import com.example.calendar.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * SELECT count(*) FROM comments WHERE scheduleId = scheduleId
     *
     * @param scheduleId 일정 고유 식별 번호
     * @return 해당 일정에 있는 댓글의 갯수
     */
    int countByScheduleId(Long scheduleId);

    /**
     * SELECT * FROM comments WHERE scheduleId = scheduleId
     *
     * @param scheduleId 일정 고유 식별 번호
     * @return id에 해당하는 일정에 존재하는 모든 댓글
     */
    List<Comment> findAllCommentsByScheduleId(Long scheduleId);
}
