package com.example.Demo.DTO;

import com.example.Demo.Model.Event;
import com.example.Demo.Model.User;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class RateDTO {

    private Long rateid;

    @NotNull
    @Max(5)
    private int rate;

    @NotNull
    @Size(min = 10, max = 50)
    private String comment;

    @NotNull
    private User user;

    @NotNull
    private Event event;

    public Long getRateid() {
        return rateid;
    }

    public void setRateid(Long rateid) {
        this.rateid = rateid;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
