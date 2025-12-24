package com.example.onlineqasping.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private String id;
    private String username;

    @JsonIgnore
    private String password;
    private int age;

    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }
}