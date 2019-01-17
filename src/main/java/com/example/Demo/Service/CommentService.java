package com.example.Demo.Service;

import com.example.Demo.DTO.CommentDTO;
import com.example.Demo.Model.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    CommentDTO addComment(CommentDTO commentDTO, Long uid, Long eid);
    List<CommentDTO> getAllComment(Long id);
    CommentDTO getComment(Long id);
    CommentDTO updateComment(CommentDTO commentDTO, Long id);
    void deleteComment(Long id);
    void deleteAll();
}
