package com.example.calendar.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "calendars")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calendar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calendarName;
    private String calendarContents;
    private String writerName;
    private String password;

    public Calendar(String calendarName, String calendarContents, String writerName, String password) {
        this.calendarName = calendarName;
        this.calendarContents = calendarContents;
        this.writerName = writerName;
        this.password = password;
    }

    public void updateCalendar(String calendarName, String writerName){
        this.calendarName = calendarName;
        this.writerName = writerName;
    }
}
