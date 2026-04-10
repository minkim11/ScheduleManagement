package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.*;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<ReadScheduleResponse> readAllSchedules(String userName) {
        List<Schedule> schedules;
        if (userName == null) {
            schedules = scheduleRepository.findAll(Sort.by("modifiedAt").descending());
        } else {
            schedules = scheduleRepository.findAllByUserName(userName, Sort.by("modifiedAt").descending());
        }
        return schedules.stream()
                .map(schedule -> new ReadScheduleResponse(
                    schedule.getScheduleName(),
                    schedule.getScheduleId(),
                    schedule.getDescription(),
                    schedule.getUserName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()))
                .toList();
    }

    @Transactional(readOnly = true)
    public ReadScheduleResponse readOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정")
        );
        return new ReadScheduleResponse(
                schedule.getScheduleName(),
                schedule.getScheduleId(),
                schedule.getDescription(),
                schedule.getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정")
        );
        if (request.getPassword().equals(schedule.getPassword())) {
            schedule.updateSchedule(request.getScheduleName(), request.getUserName(), request.getPassword());
        }

        return new UpdateScheduleResponse(
                schedule.getUserName(),
                schedule.getScheduleId(),
                schedule.getScheduleName(),
                schedule.getModifiedAt()
        );
    }
}
