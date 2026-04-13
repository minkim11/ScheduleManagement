package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.CreateCommentRequest;
import com.example.schedulemanagement.dto.CreateCommentResponse;
import com.example.schedulemanagement.entity.Comment;
import com.example.schedulemanagement.repository.CommentRepository;
import com.example.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 생성
    @Transactional
    public CreateCommentResponse createComment(Long scheduleId, CreateCommentRequest request) {
        // 일정 존재하는지 확인
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new IllegalStateException("존재하지 않는 일정");
        }
        // 최대 댓글 개수 10개 제한
        // 댓글DB에서 경로변수로 받은 일정 id 개수 확인 후 10개 이상일 경우 예외 발생
        if (commentRepository.countByScheduleId(scheduleId) >= 10) {
            throw new IllegalStateException("해당 일정 댓글 10개");
        }
        // 요청 dto로 댓글 객체 생성
        Comment comment = new Comment(
                request.getComment(),
                request.getUserName(),
                request.getPassword(),
                scheduleId
        );
        // 댓글 DB에 저장
        Comment savedComment = commentRepository.save(comment);

        // 저장된 댓글로 응답 dto 생성 후 반환
        return new CreateCommentResponse(
                savedComment.getCommentId(),
                savedComment.getComment(),
                savedComment.getUserName(),
                savedComment.getScheduleId(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }
}
