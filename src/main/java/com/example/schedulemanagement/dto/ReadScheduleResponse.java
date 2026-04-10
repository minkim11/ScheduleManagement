package com.example.schedulemanagement.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReadScheduleResponse {
    private final String userName;
    private final Long scheduleId;
    private final String scheduleName;
    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ReadScheduleResponse(String scheduleName, Long scheduleId, String description, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.userName = userName;
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
