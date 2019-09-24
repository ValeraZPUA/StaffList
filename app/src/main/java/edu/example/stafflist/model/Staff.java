package edu.example.stafflist.model;

import java.util.UUID;

public class Staff {

    private UUID id;
    private String name;
    private String phoneNumber;
    private int age;
    private String gender;

    public Staff(String name, String phoneNumber, int age, String gender) {
        id =UUID.randomUUID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
