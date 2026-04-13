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
    // DB 필수값 지정, 길이 제한
    @Column(nullable = false, length = 30)
    private String scheduleName;
    @Column(nullable = false, length = 200)
    private String description;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;

    public Schedule(String scheduleName, String description, String userName, String password) {
        // 일정제목, 내용 공백, null일 경우 예외 발생 및 길이 제한
        if (scheduleName == null || scheduleName.isBlank() || 30 < scheduleName.length()) {
            throw new IllegalArgumentException("일정 제목은 필수입니다. 최대 30자 이내");
        }
        if (description == null || description.isBlank() || 200 < description.length()) {
            throw new IllegalArgumentException("일정 내용은 필수입니다. 최대 200자 이내");
        }
        // 작성자명, 비밀번호는 공백, null일 경우 예외 발생
        if (userName == null || userName.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("작성자명과 비밀번호는 필수입니다.");
        }
        this.scheduleName = scheduleName;
        this.description = description;
        this.userName = userName;
        this.password = password;
    }

    public void updateSchedule(String scheduleName, String userName, String password) {
        // 비밀번호 일치 시 업데이트
        if (password.equals(this.password)) {
            this.scheduleName = scheduleName;
            this.userName = userName;
        }
    }

}
