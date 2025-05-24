package com.kunge2013.spring.transaction.entity;

import lombok.Data;

/**
 * @Author kunge2013
 * @Description TODO
 * @Date 2025/5/24 20:04
 * @Version 1.0
 */
public class User {
    private Integer id;
    private String username;
    private Integer age;

    public User() {}

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
}