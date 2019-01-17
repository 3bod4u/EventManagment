package com.example.Demo.DTO;

import com.example.Demo.Model.Event;
import com.example.Demo.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


public class CommentDTO {

    private Long commentid;

    @NotNull
    @Size(min = 10, max = 50)
    private String comment;

    @NotNull
    private LocalDate date = LocalDate.now();

    @NotNull
    private Event event;

    @NotNull
    private User user;

    public Long getCommentid() {
        return commentid;
    }

    public void setCommentid(Long commentid) {
        this.commentid = commentid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
