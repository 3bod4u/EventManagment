package com.example.Demo.ServiceImp;

import com.example.Demo.DTO.CommentDTO;
import com.example.Demo.Model.Comment;
import com.example.Demo.Repository.CommentRepository;
import com.example.Demo.Repository.EventRepository;
import com.example.Demo.Repository.UserRepository;
import com.example.Demo.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentImp implements CommentService {

    @Autowired
    public CommentRepository commentRepository;
    @Autowired
    public EventRepository eventRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public NotificationServiceImp notificationServiceImp;
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<CommentDTO> getAllComment(Long id) {

        List<Comment> commentEntitys = commentRepository.findAllByAvailabilityIsTrueAndAndEvent_Eventid(id);
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for(Comment comment : commentEntitys){
            CommentDTO commentDTO = modelMapper.map(comment,CommentDTO.class);
            commentDTOs.add(commentDTO);
        }
        return commentDTOs;
    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO, Long userId, Long eventId) {
        Comment comment = modelMapper.map(commentDTO,Comment.class);
        comment.setUser(userRepository.findById(userId).get());
        comment.setEvent(eventRepository.findById(eventId).get());
        comment.setDate(LocalDate.now());
        if(comment.getEvent().getDate().isAfter(LocalDate.now())){
            notificationServiceImp.sendNotificaitoin(comment.getEvent().getUser(),"Comment added to "+comment.getEvent().getTitle(),"Comment added to "+comment.getEvent().getTitle());
            commentRepository.save(comment);
            return commentDTO;
        }
        return null;
    }

    @Override
    public CommentDTO getComment(Long id) {
        Comment comment = commentRepository.findById(id).get();
        CommentDTO commentDTO = modelMapper.map(comment,CommentDTO.class);
        return commentDTO;
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO, Long id) {
        Comment comment = commentRepository.findById(id).get();
        Comment comment1 = modelMapper.map(commentDTO,Comment.class);
        comment1.setCommentid(comment.getCommentid());
        comment1.setAvailability(comment.isAvailability());
        return commentDTO;
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).get();
        comment.setAvailability(false);
    }

    @Override
    public void deleteAll() {
    commentRepository.deleteAll();
    commentRepository.flush();
    }
}
