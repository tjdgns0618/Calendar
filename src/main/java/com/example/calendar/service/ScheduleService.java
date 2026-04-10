package com.example.calendar.service;

import com.example.calendar.dto.*;
import com.example.calendar.entity.Schedule;
import com.example.calendar.exception.ScheduleNotFoundException;
import com.example.calendar.exception.PasswordNotMatchException;
import com.example.calendar.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 레포지터리 빈과 연결해준다.
    // 스프링이 자동으로 필요한 의존성을 주입하여 객체 간의 관계를 구성해준다.
    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    /**
     * 생성 요청에 대한 생성 응답 DTO를 반환해주는 함수
     *
     * @param request
     * @return 생성 응답 DTO
     */
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getScheduleName(),
                request.getScheduleContents(),
                request.getAuthor(),
                request.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getScheduleName(),
                savedSchedule.getScheduleContent(),
                savedSchedule.getAuthor(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    /**
     * 이름을 Key로 받는 조회 요청에 대한 모든 응답 DTO를 반환하는 함수
     *
     * @param author 작성자명
     * @return Name에 해당하는 모든 일정들 DTO
     */
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAllByAuthor(String author) {
        List<Schedule> schedules = scheduleRepository.findAllByAuthor(author);
        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getScheduleName(),
                    schedule.getScheduleContent(),
                    schedule.getAuthor(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        Sort.by(Sort.Direction.DESC, "modifiedAt");
        return dtos;
    }

    /**
     * id에 해당하는 일정을 조회하는 함수
     *
     * @param id 일정 고유번호
     * @return id에 해당하는 응답 DTO
     */
    @Transactional(readOnly = true)
    public GetScheduleResponse findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getScheduleName(),
                schedule.getScheduleContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    /**
     * id에 해당하는 일정의 이름과 작성자명을 변경하는 함수
     *
     * @param id 일정 고유번호
     * @param request 업데이트 요청 DTO
     * @return 변경된 데이터 응답 DTO
     */
    @Transactional
    public UpdateScheduleResponse update(Long id, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        // 나눠보기 (메서드 체이닝 상태)
        boolean correctPassword = schedule.getPassword().equals(request.getPassword());
        if (!correctPassword) {
            throw new PasswordNotMatchException("맞지 않는 비밀번호입니다.");
        }

        schedule.updateCalendar(request.getScheduleName(), request.getAuthor());

        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getScheduleName(),
                schedule.getScheduleContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    /**
     * id와 요청 DTO의 비밀번호를 검사하여 일치한다면 삭제를 진행하는 함수
     *
     * @param id
     * @param request
     */
    @Transactional
    public void delete(Long id, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        boolean correctPassword = schedule.getPassword().equals(request.getPassword());

        if (!correctPassword) {
            throw new PasswordNotMatchException("맞지 않는 비밀번호입니다.");
        }

        scheduleRepository.deleteById(id);
    }

}
