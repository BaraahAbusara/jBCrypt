package com.example.jBCrypt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@JsonIgnoreProperties({"myUser"})

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String textContent;

    @ManyToOne
    private MyUser myUser;

    public Recipe(String textContent) {
        this.textContent = textContent;
    }

    public Recipe() {
    }

    public Long getId() {
        return id;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "textContent='" + textContent + '\'' +
                '}';
    }
}
