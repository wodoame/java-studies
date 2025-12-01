package com.amalitech;

import com.amalitech.model.User;
import com.amalitech.service.UserService;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();

        User nullUser = null;
        User validUser = new User("Naana", "naana@example.com", 25);

        System.out.println("=== Starting Demo ===");

        try {
//            service.processUser(nullUser);
            service.processUser(validUser);
        } catch (Exception ex) {
            System.err.println("Top-level exception caught: " + ex.getMessage());
        }

        System.out.println("=== Demo Ended ===");
    }
}