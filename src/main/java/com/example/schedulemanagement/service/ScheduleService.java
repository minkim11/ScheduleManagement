package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.*;
import com.example.schedulemanagement.entity.Comment;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.CommentRepository;
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
    private final CommentRepository commentRepository;

    // 일정 생성
    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        // 1. 요청 dto의 데이터로 객체 생성
        Schedule schedule = new Schedule(
                request.getScheduleName(),
                request.getDescription(),
                request.getUserName(),
                request.getPassword());
        // 2. 생성한 객체 DB에 저장
        Schedule savedSchedule = scheduleRepository.save(schedule);
        // 3. 저장한 객체 데이터로 응답 dto 생성 후 반환
        return new CreateScheduleResponse(
                savedSchedule.getScheduleId(),
                savedSchedule.getScheduleName(),
                savedSchedule.getDescription(),
                savedSchedule.getUserName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
                );
    }

    // 일정 전체 조회
    @Transactional(readOnly = true)
    public List<ReadScheduleResponse> readAllSchedules(String userName) {
        List<Schedule> schedules;
        // 1. 조회조건으로 작성자명 없을 시 전체 조회, 있을 시 해당 작성자 일정 목록 조회
        if (userName == null) {
            schedules = scheduleRepository.findAll(Sort.by("modifiedAt").descending());
        } else {
            schedules = scheduleRepository.findAllByUserName(userName, Sort.by("modifiedAt").descending());
        }
        // 2. 응답 dto 목록 생성 후 반환
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

    // 일정 선택 조회
    @Transactional(readOnly = true)
    public ReadOneScheduleResponse readOneSchedule(Long scheduleId) {
        // 1. 경로 변수에 따라 일정 가져옴
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정")
        );
        // 2. 일정 id에 맞는 댓글 목록 가져옴
        List<Comment> comments = commentRepository.findAllByScheduleId(scheduleId);
        // 3. 응답 dto 생성 후 반환
        return new ReadOneScheduleResponse(
                schedule.getScheduleName(),
                schedule.getScheduleId(),
                schedule.getDescription(),
                schedule.getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                comments
                );
    }

    // 일정 수정
    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) throws IllegalStateException {
        // 1. 경로 변수에 따라 일정 가져옴
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정")
        );
        // 2. 요청 dto의 비밀번호와 DB의 비밀번호 일치 시 수정
        if (request.getPassword().equals(schedule.getPassword())) {
            schedule.updateSchedule(request.getScheduleName(), request.getUserName(), request.getPassword());
        } else {
            throw new IllegalStateException("패스워드 오류");
        }
        // 3. 수정한 객체 응답 dto 생성 후 반환
        return new UpdateScheduleResponse(
                schedule.getUserName(),
                schedule.getScheduleId(),
                schedule.getScheduleName(),
                schedule.getModifiedAt()
        );
    }

    // 일정 삭제
    @Transactional
    public void deleteSchedule(Long scheduleId, DeleteScheduleRequest request) throws IllegalStateException {
        // 1. 경로 변수 일정 가져옴
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정")
        );
        // 2. 비밀번호 맞을 경우 삭제
        if (request.getPassword().equals(schedule.getPassword())) {
            scheduleRepository.deleteById(scheduleId);
        } else {
            throw new IllegalStateException("패스워드 오류");
        }
    }
}
