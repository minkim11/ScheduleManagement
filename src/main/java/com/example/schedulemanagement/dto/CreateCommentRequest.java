package com.example.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String comment;
    private String userName;
    private String password;
}
