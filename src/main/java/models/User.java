package models;

import org.sql2o.Connection;

import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private int phonenumber;

    public User(String username, String password, String email, int phonenumber) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.phonenumber=phonenumber;
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


}
