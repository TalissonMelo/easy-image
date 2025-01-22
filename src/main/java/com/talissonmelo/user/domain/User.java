package com.talissonmelo.user.domain;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "tb_user")
public class User {

    @MongoId
    private String userId;

    private String email;

    private String name;

    private String password;

    private LocalDateTime uploadDate = LocalDateTime.now();


    private User(String userId, String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.password = password;
    }

    public static User to(String name, String email, String password) {
        return new User(UUID.randomUUID().toString(), name, email, password);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
}
