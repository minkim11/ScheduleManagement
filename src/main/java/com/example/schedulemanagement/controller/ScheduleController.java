package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.*;
import com.example.schedulemanagement.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @RequestBody CreateScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(request));
    }

    // 일정 전체 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<ReadScheduleResponse>> readAllSchedules(
            //작성자명 기준으로 일정 목록 조회, 조회 조건에 없을 시 전체 조회
            @RequestParam(name = "username", required = false) String userName
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.readAllSchedules(userName));
    }

    // 일정 선택 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ReadOneScheduleResponse> readOneSchedule(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.readOneSchedule(scheduleId));
    }

    // 일정 수정(일정명, 작성자명만 수정 가능)
    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(scheduleId, request));
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestBody DeleteScheduleRequest request
    ) {
        scheduleService.deleteSchedule(scheduleId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
