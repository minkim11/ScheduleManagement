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
    // DB 필수값, 길이 제한
    @Column(nullable = false, length = 100)
    private String comment;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    private Long scheduleId;

    public Comment(String comment, String userName, String password, Long scheduleId) {
        // 댓글 길이 제한 및 공백이거나 null일 경우 예외 발생
        if (comment == null || comment.isBlank() || 100 < comment.length()) {
            throw new IllegalArgumentException("댓글 내용은 필수입니다. 최대 100자 이내");
        }
        // 작성자명, 비밀번호는 공백, null일 경우 예외 발생
        if (userName == null || userName.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("작성자명과 비밀번호는 필수입니다.");
        }
        this.comment = comment;
        this.userName = userName;
        this.password = password;
        this.scheduleId = scheduleId;
    }
}
