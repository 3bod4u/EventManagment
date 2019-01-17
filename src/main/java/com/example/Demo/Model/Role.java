package com.example.Demo.Model;

import org.springframework.validation.annotation.Validated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ROLES")
@Validated
public class Role {

    @Id
    @Column(length = 50, unique = true)
    private String usertype;

    @NotNull
    private boolean availability = true;

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}