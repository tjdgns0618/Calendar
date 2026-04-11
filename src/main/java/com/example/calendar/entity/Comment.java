package com.example.calendar.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(length = 100, nullable = false)
    private String commentContents;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Long scheduleId;

    public Comment(String commentContent, String author, String password, Long scheduleId) {
        this.commentContents = commentContent;
        this.author = author;
        this.password = password;
        this.scheduleId = scheduleId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getCommentContents() {
        return commentContents;
    }

    public String getAuthor() {
        return author;
    }

    public String getPassword() {
        return password;
    }

    public Long getScheduleId() {
        return scheduleId;
    }
}
