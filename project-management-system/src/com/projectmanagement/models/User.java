package com.projectmanagement.models;

public abstract class User {
    private static int idCounter = 1;

    private final int id;
    private final String role;
    private String name;
    private String email;

    protected User(String role, String name, String email) {
        this.id = idCounter++;
        this.role = role;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract boolean canModify();

    public void displayProfile() {
        System.out.printf("[#%d] %s (%s)%nEmail: %s%n", id, name, role, email);
    }
}

