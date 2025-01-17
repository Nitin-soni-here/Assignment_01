package com.Assignment;

import javax.validation.constraints.NotBlank;

public class LoginDTO {
    private int id;
    // @NotBlank(message = "Not Be Blank")
    private  String username;
    //  @NotBlank(message = "Not Be Blank")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}