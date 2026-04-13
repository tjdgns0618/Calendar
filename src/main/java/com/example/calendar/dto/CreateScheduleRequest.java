package com.example.calendar.dto;

// @Getter 가 없다면???
// Jackson이라는 라이브러리가 JSON으로 통역하려 하는데 private로 되어있으면 찾지 못해 500 Error가 발생한다.
// Jackson이 무엇인가?? 프론트엔드가 자바를 모름에도 불구하고 JSON 형태로 받을수 있도록
// 자바 객체를 번역해주는 역할을 하는 라이브러리이다.
public class CreateScheduleRequest {
    private final String scheduleName;
    private final String scheduleContents;
    private final String author;
    private final String password;

    public CreateScheduleRequest(String scheduleName, String scheduleContents, String author, String password) {
        this.scheduleName = scheduleName;
        this.scheduleContents = scheduleContents;
        this.author = author;
        this.password = password;
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
