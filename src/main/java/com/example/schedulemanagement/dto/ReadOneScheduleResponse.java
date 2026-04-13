package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Comment;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReadOneScheduleResponse {
    private final String userName;
    private final Long scheduleId;
    private final String scheduleName;
    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<Comment> comments;

    public ReadOneScheduleResponse(String userName, Long scheduleId, String scheduleName, String description,
                                   LocalDateTime createdAt, LocalDateTime modifiedAt, List<Comment> comments) {
        this.userName = userName;
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
