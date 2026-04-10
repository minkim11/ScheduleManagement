package com.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String password;

    public Schedule(String scheduleName, String description, String userName, String password) {
        this.scheduleName = scheduleName;
        this.description = description;
        this.userName = userName;
        this.password = password;
    }

    public void updateSchedule(String scheduleName, String userName, String password) {
        if (password.equals(this.password)) {
            this.scheduleName = scheduleName;
            this.userName = userName;
        }
    }

}
