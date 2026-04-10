package com.example.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String scheduleName;
    private String userName;
    private String password;
}
