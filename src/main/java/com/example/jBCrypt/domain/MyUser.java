package com.example.jBCrypt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    public Long id;

    @Column(unique = true)
    String username;

    String password;

    @OneToMany(mappedBy = "myUser")
    public List <Recipe> recipes = new ArrayList<>();


    public MyUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected MyUser() {
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "MyUser{" +
                "username='" + username + '\'' +
                '}';
    }
}
