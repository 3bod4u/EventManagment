package com.example.Demo.Repository;

import com.example.Demo.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByAvailabilityIsTrueAndAndEvent_Eventid(Long id);

}
