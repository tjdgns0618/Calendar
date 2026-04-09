package com.example.calendar.repository;

import com.example.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    // 이름에 해당하는 모든 데이터 찾기용
    List<Calendar> findAllByWriterName(String writerName);
}
