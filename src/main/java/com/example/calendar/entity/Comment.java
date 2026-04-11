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
    private String author;
    private String password;
    private Long id;

    public Comment(String commentContent, String author, String password, Long id) {
        this.commentContents = commentContent;
        this.author = author;
        this.password = password;
        this.id = id;
    }
}
