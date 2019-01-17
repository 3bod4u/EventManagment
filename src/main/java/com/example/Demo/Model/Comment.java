package com.example.Demo.Model;

import org.springframework.validation.annotation.Validated;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Comments")
@Validated
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentid;

    private String comment;

    private LocalDate date;

    private boolean availability = true;

    @ManyToOne(cascade = CascadeType.ALL)
    private Event event;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
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
