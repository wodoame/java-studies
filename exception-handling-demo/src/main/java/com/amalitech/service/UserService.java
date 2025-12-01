package com.amalitech.service;

import com.amalitech.db.DatabaseService;
import com.amalitech.exceptions.ResourceFailsException;
import com.amalitech.exceptions.UserValidationException;
import com.amalitech.model.User;
import com.amalitech.resource.ResourceThatFails;

import java.sql.Connection;

public class UserService {

    private final DatabaseService db = new DatabaseService();
    private final ExternalApiService api = new ExternalApiService();

    public void processUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null!");
        }

        if (user.getAge() < 0) {
            throw new UserValidationException("Age cannot be negative");
        }

        System.out.println("Processing user: " + user.getName());

        try (ResourceThatFails r = new ResourceThatFails()) {
            Connection conn = db.safeConnect();
            api.callApi();
        }
        catch (ResourceFailsException ex) {
            System.err.println("Error in processUser: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
