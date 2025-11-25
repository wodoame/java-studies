package com.projectmanagement.models;

public class AdminUser extends User {
    public AdminUser(String name, String email) {
        super("Admin", name, email);
    }

    @Override
    public boolean canModify() {
        return true;
    }
}

