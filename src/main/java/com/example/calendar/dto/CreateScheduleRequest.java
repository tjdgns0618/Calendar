package com.example.calendar.dto;

import lombok.Getter;

// @Getter 가 없다면???
// Jackson이라는 라이브러리가 JSON으로 통역하려 하는데 private로 되어있으면 찾지 못해 500 Error가 발생한다.
// Jackson이 무엇인가?? 프론트엔드가 자바를 모름에도 불구하고 JSON 형태로 받을수 있도록
// 자바 객체를 번역해주는 역할을 하는 라이브러리이다.
@Getter
public class CreateScheduleRequest {
    private String scheduleName;
    private String scheduleContents;
    private String author;
    private String password;
}
