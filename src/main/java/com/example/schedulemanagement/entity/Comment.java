package com.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String comment;
    private String userName;
    private String password;
    private Long scheduleId;

    public Comment(String comment, String userName, String password, Long scheduleId) {
        this.comment = comment;
        this.userName = userName;
        this.password = password;
        this.scheduleId = scheduleId;
    }
}
