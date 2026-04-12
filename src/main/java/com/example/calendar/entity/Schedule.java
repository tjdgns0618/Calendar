package com.example.calendar.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    @Column(length = 30, nullable = false)
    private String scheduleName;
    @Column(length = 200, nullable = false)
    private String scheduleContents;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String password;

    public Schedule(String scheduleName, String scheduleContents, String author, String password) {
        this.scheduleName = scheduleName;
        this.scheduleContents = scheduleContents;
        this.author = author;
        this.password = password;
    }

    public void updateSchedule(String scheduleName, String author) {
        this.scheduleName = scheduleName;
        this.author = author;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public String getScheduleContents() {
        return scheduleContents;
    }

    public String getAuthor() {
        return author;
    }

    public String getPassword() {
        return password;
    }
}
