package com.example.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    private String scheduleName;
    private String description;
    private String userName;
    private String password;
}
