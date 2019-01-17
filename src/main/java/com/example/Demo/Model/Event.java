package com.example.Demo.Model;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "EVENTS")
@Validated
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventid;

    @Column(length = 30)
    private String title;

    @Column(length = 300)
    private String description;

    @Column(length = 300)
    private int seats;

    @Column(length = 15)
    private String city;

    private LocalDate date;

    @NotNull
    private boolean approved = false;

    @NotNull
    private boolean availability = true;

    @NotNull
    private Long counter;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    private User user;

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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
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

}
