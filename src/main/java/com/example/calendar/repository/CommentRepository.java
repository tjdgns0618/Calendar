package com.example.calendar.repository;

import com.example.calendar.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * SELECT count(*) FROM comment WHERE Id
     * @param scheduleId 일정의 식별번호
     * @return 해당 일정에 있는 댓글의 갯수
     */
    int countById(Long scheduleId);
}
