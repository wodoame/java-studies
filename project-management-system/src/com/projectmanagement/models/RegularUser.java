package com.projectmanagement.models;

public class RegularUser extends User {
    public RegularUser(String name, String email) {
        super("Regular", name, email);
    }

    @Override
    public boolean canModify() {
        return false;
    }
}

