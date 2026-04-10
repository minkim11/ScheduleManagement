package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.CreateScheduleRequest;
import com.example.schedulemanagement.dto.CreateScheduleResponse;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getScheduleName(),
                request.getDescription(),
                request.getUserName(),
                request.getPassword());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getScheduleId(),
                savedSchedule.getScheduleName(),
                savedSchedule.getDescription(),
                savedSchedule.getUserName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
                );
    }
}
