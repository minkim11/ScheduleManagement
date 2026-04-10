package com.example.schedulemanagement.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {
    private final String userName;
    private final Long scheduleId;
    private final String scheduleName;
    private final LocalDateTime modifiedAt;

    public UpdateScheduleResponse(String userName, Long scheduleId, String scheduleName, LocalDateTime modifiedAt) {
        this.userName = userName;
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.modifiedAt = modifiedAt;
    }
}
