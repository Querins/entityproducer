package com.example.kafkaproducer.entity;

public class User {

    private String name;
    private int age;
    private int id;

    public int getId() {
        return id;
    }

    public User(int id, String name, int age) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
