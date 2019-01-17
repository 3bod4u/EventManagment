package com.example.Demo.DTO;

import com.example.Demo.Model.Event;
import com.example.Demo.Model.User;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


public class TicketDTO {

    private Long ticketid ;

    private String ticketPass;

    private User user;

    private Event event;

    private String title;

    private LocalDate date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getTicketid() {
        return ticketid;
    }

    public void setTicketid(Long ticketid) {
        this.ticketid = ticketid;
    }

    public String getTicketPass() {
        return ticketPass;
    }

    public void setTicketPass(String ticketPass) {
        this.ticketPass = ticketPass;
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
