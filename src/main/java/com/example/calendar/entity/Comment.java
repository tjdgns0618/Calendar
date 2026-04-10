package com.example.calendar.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String commentContents;
    private String writerName;
    private String password;
    private Long id;

    public Comment(String commentContent, String writerName, String password) {
        this.commentContents = commentContent;
        this.writerName = writerName;
        this.password = password;
    }
}
