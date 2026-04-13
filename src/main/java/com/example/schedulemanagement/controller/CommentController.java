package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.CreateCommentRequest;
import com.example.schedulemanagement.dto.CreateCommentResponse;
import com.example.schedulemanagement.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 일정에 댓글 작성
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(
            // 경로 변수로 일정id 받아서 댓글 생성
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(scheduleId, request));
    }
}
