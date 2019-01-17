package com.example.Demo.Model;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "TICKETS")
@Validated
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketid;

    @NotNull
    private String ticketPass;

    private boolean availability = true;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
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
