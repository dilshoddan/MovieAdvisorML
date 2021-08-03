package com.movieadvisor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User {
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username) {
        this.username = username;
    }
}
