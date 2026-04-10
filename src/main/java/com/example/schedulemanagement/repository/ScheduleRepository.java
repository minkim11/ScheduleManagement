package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.entity.Schedule;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
    // 쿼리 생성 : 작성자명과 일치하는 일정 목록 반환하는 메서드
    List<Schedule> findAllByUserName(String userName, Sort sort);
}
