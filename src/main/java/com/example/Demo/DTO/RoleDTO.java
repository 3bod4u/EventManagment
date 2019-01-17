package com.example.Demo.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoleDTO {

    @NotNull
    @Size(min = 4, max = 50)
    private String usertype;

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}