package com.example.calendar.repository;

import com.example.calendar.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 이름에 해당하는 모든 데이터 찾기용
    List<Schedule> findAllByAuthor(String author);
}
