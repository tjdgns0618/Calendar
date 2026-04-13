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
     * 생성 요청에 대한 생성 응답 DTO를 반환해주는 메서드
     *
     * @param request 일정 생성 요청 DTO
     * @return DB에 정상적으로 저장된 일정 응답 DTO
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
                savedSchedule.getScheduleId(),
                savedSchedule.getScheduleName(),
                savedSchedule.getScheduleContents(),
                savedSchedule.getAuthor(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    /**
     * 이름을 Key로 받는 조회 요청에 대한 모든 응답 DTO를 반환하는 메서드
     *
     * @param author 작성자명
     * @return 작성자명에 해당하는 모든 일정 응답 DTO들
     */
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAllByAuthor(String author) {
        List<Schedule> schedules = scheduleRepository.findAllSchedulesByAuthor(author);
        List<GetScheduleResponse> scheduleResponses = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse scheduleResponse = new GetScheduleResponse(
                    schedule.getScheduleId(),
                    schedule.getScheduleName(),
                    schedule.getScheduleContents(),
                    schedule.getAuthor(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            scheduleResponses.add(scheduleResponse);
        }
        Sort.by(Sort.Direction.DESC, "modifiedAt");
        return scheduleResponses;
    }

    /**
     * id에 해당하는 일정을 조회하는 메서드
     *
     * @param scheduleId 일정 고유 식별 번호
     * @return id에 해당하는 응답 DTO
     */
    @Transactional(readOnly = true)
    public GetScheduleWithCommentsResponse findOneById(Long scheduleId, List<GetCommentResponse> comments) {
        Schedule schedule = findScheduleById(scheduleId);

        return new GetScheduleWithCommentsResponse(
                schedule.getScheduleId(),
                schedule.getScheduleName(),
                schedule.getScheduleContents(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                comments
        );
    }

    /**
     * id에 해당하는 일정의 이름과 작성자명을 변경하는 메서드
     *
     * @param scheduleId 일정 고유 식별 번호
     * @param request    업데이트 요청 DTO
     * @return DB에 변경 성공된 데이터 응답 DTO
     */
    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = findScheduleById(scheduleId);

        String requestPassword = request.getPassword();

        schedule.passwordAuthentication(requestPassword);

        String updateRequestName = request.getScheduleName();
        String updateAuthor = request.getAuthor();

        schedule.updateSchedule(updateRequestName, updateAuthor);

        return new UpdateScheduleResponse(
                schedule.getScheduleId(),
                schedule.getScheduleName(),
                schedule.getScheduleContents(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    /**
     * ID와 요청 DTO의 비밀번호를 검사하여 일치한다면 삭제를 진행하는 메서드
     *
     * @param scheduleId      일정 고유 식별 번호
     * @param request 비밀번호 확인용 요청 DTO
     */
    @Transactional
    public void delete(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = findScheduleById(scheduleId);

        String requestPassword = request.getPassword();

        schedule.passwordAuthentication(requestPassword);

        scheduleRepository.deleteById(scheduleId);
    }

    /**
     * ID에 해당하는 일정이 존재하는지 검사하는 메서드
     *
     * @param scheduleId 일정 고유 식별 번호
     */
    @Transactional(readOnly = true)
    public void checkScheduleExistenceById(Long scheduleId) {
        boolean existence = scheduleRepository.existsById(scheduleId);
        if (!existence) {
            throw new ScheduleNotFoundException("존재하지 않는 일정입니다.");
        }
    }

    @Transactional
    public Schedule findScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
    }
}
