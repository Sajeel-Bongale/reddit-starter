package com.sajeelbong.reddit.entity;

public class User {

    private String name;

    private int numberOfFriends;

    public User() {
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", numberOfFriends=" + numberOfFriends +
                '}';
    }

    public User(String name, int numberOfFriends) {
        this.name = name;
        this.numberOfFriends = numberOfFriends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfFriends() {
        return numberOfFriends;
    }

    public void setNumberOfFriends(int numberOfFriends) {
        this.numberOfFriends = numberOfFriends;
    }
}
