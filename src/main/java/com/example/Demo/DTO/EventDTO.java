package com.example.Demo.DTO;

import com.example.Demo.Model.Comment;
import com.example.Demo.Model.User;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;


public class EventDTO {

    private Long eventid;

    @NotNull
    @Size(min = 4 , max = 30)
    private String title;

    @NotNull
    @Size(min = 30 , max = 300)
    private String description;

    @NotNull
    @Max(300)
    private int seats;

    @NotNull
    @Size(min = 3, max = 15)
    private String city;

    @NotNull
    private LocalDate date;

    private boolean approved;

    private Long counter;

    private User user;

    private List<Comment> comment;

    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

}
