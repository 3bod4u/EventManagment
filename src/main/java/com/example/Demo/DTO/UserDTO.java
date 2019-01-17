package com.example.Demo.DTO;

import com.example.Demo.Model.Role;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
public class UserDTO {

    private Long userid;

    @NotNull
    @Size(min = 4,max = 20)
    private String firstname;

    @NotNull
    @Size(min = 4,max = 20)
    private String lastname;

    @NotNull
    @Size(min = 4,max = 50)
    private String email;

    @NotNull
    @Size(min = 10,max = 10)
    private String phone;

    @NotNull
    @Size(min = 8,max = 24)
    private String password;

    private Role role;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
