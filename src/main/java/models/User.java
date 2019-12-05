package models;

import org.sql2o.Connection;

import java.util.List;

public class User {
    private int id;
    private String username;
    private String email;
    private int phonenumber;
    private String password;


    public User(String username, String email, int phonenumber, String password) {
        this.username = username;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }
}
