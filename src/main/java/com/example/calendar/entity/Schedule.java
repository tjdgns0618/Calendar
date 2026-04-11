package com.example.calendar.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//
@Entity
//
@Table(name = "schedules")
//
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private String scheduleName;
    private String scheduleContent;
    private String author;
    private String password;

    public Schedule(String scheduleName, String scheduleContent, String author, String password) {
        this.scheduleName = scheduleName;
        this.scheduleContent = scheduleContent;
        this.author = author;
        this.password = password;
    }

    public void updateSchedule(String scheduleName, String author){
        this.scheduleName = scheduleName;
        this.author = author;
    }
}
