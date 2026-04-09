package com.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private String scheduleName;
    private String description;
    private String userName;

    public Schedule(String scheduleName, String description, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleName = scheduleName;
        this.description = description;
        this.userName = userName;
    }

}
