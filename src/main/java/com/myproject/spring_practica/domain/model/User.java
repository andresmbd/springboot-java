package com.myproject.spring_practica.domain.model;

public class User {
    private Long id;
    private String firstName;
    private String lastName;

    public User(Long id, String firstName,  String lastName) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
}
