package com.example.calendar.controller;


import com.example.calendar.dto.*;
import com.example.calendar.service.CommentService;
import com.example.calendar.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RestController 어노테이션은 왜 사용하는가?
// 클라이언트에게 화면이 아니라 순수한 데이터(JSON)만을 보내주기 위해서 만든 어노테이션이다.
// 본래의 Controller : HTML 페이지로 가라는 명령으로 사용자를 특정 화면으로 보냈었다.
// RestController : JSON 데이터만 보내주고 화면 꾸미기는 프론트엔드에게 맞기는 방식용
// @RestController = @Controller + @ResponseBody
@RestController
// @RequiredArgsConstructor 어노테이션은 어떤 일을 하는가?
// 필드에 존재하는 final 필드들을 생성자에서 자동으로 초기화해주고 의존성 주입과 객체간의 연결

// @PostMapping같은 어노테이션들의 상위 어노테이션으로 어떤 메서드를 이행할지는 정하지 않고 url만 매핑해주었다.
// 물론 메서드도 속성으로 매핑이 가능하다. (value = "/schedules", method = RequestMethod.GET) 등
@RequestMapping("/schedules")
public class ScheduleController {
    // 컨트롤러에서는 서비스와의 상호작용만 한다.
    private final ScheduleService scheduleService;
    private final CommentService commentService;

    // @Autowired 서비스 빈과 연결해주고 있다.
    // 스프링이 자동으로 필요한 의존성을 주입하여 객체 간의 관계를 구성해준다.
    @Autowired
    public ScheduleController(ScheduleService scheduleService, CommentService commentService) {
        this.scheduleService = scheduleService;
        this.commentService = commentService;
    }

    /**
     * 일정을 생성하는 API
     *
     * @param request 일정 생성 요청 DTO
     * @return 일정 정보가 담긴 객체
     */
    // @RequestMapping(method = RequestMethod.POST)을 쓰기 편하게 미리 포장해 둔 단축키 어노테이션이다.
    // @RequestMapping을 아예 안쓰는 것이 아닌 공통으로 사용할 매핑의 주소를 정해두고 나눌수도 있다.
    // @RequestMapping("/calendars") public class CalendarController { @PostMapping - @GetMapping("/{id}") }
    @PostMapping
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {

        // 1. 서비스에서 비즈니스 로직을 실행하고, 완성된 DTO를 받아서 초기화
        CreateScheduleResponse response = scheduleService.save(request);

        // 2. BodyBuilder형 변수 builder 생성하고 HttpStatus.CREATED를 저장해준다.
        // ResponseEntity 안에 BodyBuilder가 있는 이유는 다른곳에서는
        // 절대 안쓰고 ResponseEntity 안에서만 사용할거라고 생각하고 만들었기 때문
        // 이는 완전히 완성된 응답용 객체가 아닌 조립중인 BodyVBuilder이다.
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.CREATED);

        // CREATED가 찍혀있는 BodyBuilder에 DTO를 집어 넣는다.
        // ResponseEntity<T> body 함수의 역할 : 엔티티의 바디를 T타입의 매개변수로 채워주고 반환해준다.
        ResponseEntity<CreateScheduleResponse> responseEntity = builder.body(response);

        return responseEntity;
    }

    /**
     * 이름을 key로 사용하여 작성자 이름에 해당하는 모든 일정을 반환해주는 메서드
     *
     * @param author 쿼리스트링의 key
     * @return key에 해당하는 작성자의 모든 일정 정보가 담긴 객체
     * @RequestParam에 required 속성을 변경해주어 있어도 없어도 되게 해주었습니다. 이로인해 다른 GetMapping 어노테이션이 작동이 가능해졌습니다.
     */
    // @RequestMapping(method = RequestMethod.GET)을 쓰기 편하게 미리 포장해 둔 단축키 어노테이션이다.
    @GetMapping()
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedule(@RequestParam(required = false) String author) {
        // 1. 비즈니스 로직 후 결과물 받아오기
        List<GetScheduleResponse> response = scheduleService.findAllByAuthor(author);

        // 2. ResponseEntity를 만들때, 결과물과 상태코드를 한 번에 담아서 반환하는 방식
        // 생성자를 이용해서 바디와 상태코드를 함께 넣어서 보내주는 형태 물론 생성자는 여러가지 형태로 오버로딩 되어있다.
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * scheduleId를 경로로 해당하는 일정을 반환해주는 메서드
     *
     * @param scheduleId 일정의 고유 식별 번호
     * @return 해당 id에 대한 일정 정보와 댓글 정보 목록이 담긴 객체
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetScheduleWithCommentsResponse> getScheduleById(@PathVariable Long scheduleId) {
        //
        List<GetCommentResponse> comments = commentService.getAllCommentsByScheduleId(scheduleId);

        GetScheduleWithCommentsResponse response = scheduleService.findOneById(scheduleId, comments);

        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.OK);

        ResponseEntity<GetScheduleWithCommentsResponse> responseEntity = builder.body(response);

        return responseEntity;
    }

    /**
     * id를 경로로 해당하는 일정을 변경해주는 메서드
     *
     * @param scheduleId 일정 고유 식별 번호
     * @param request    수정 요청 일정 DTO
     * @return 변경된 일정의 정보가 담긴 객체
     */
    @PutMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateScheduleById(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
    ) {
        UpdateScheduleResponse response = scheduleService.update(scheduleId, request);

        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.OK);

        ResponseEntity<UpdateScheduleResponse> responseEntity = builder.body(response);

        return responseEntity;
    }

    /**
     * id를 경로로 해당하는 일정을 삭제하는 메서드
     *
     * @param scheduleId 일정의 고유 식별 번호
     * @return NO_CONTENT
     */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteScheduleById(
            @PathVariable Long scheduleId,
            @RequestBody DeleteScheduleRequest request
    ) {
        scheduleService.delete(scheduleId, request);

        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.NO_CONTENT);
        // 삭제 API에서는 바디가 존재하지 않기 때문에 build()
        // build() : 응답 엔티티를 바디없이 만드는 함수
        return builder.build();
    }
}
