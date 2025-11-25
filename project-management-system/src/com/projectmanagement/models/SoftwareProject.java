package com.projectmanagement.models;

public class SoftwareProject extends Project {
    public SoftwareProject(int id, String name, String description, double budget, int teamSize, int maxTasks) {
        super(id, "Software", name, description, budget, teamSize, maxTasks);
    }

    @Override
    public String getProjectDetails() {
        return String.format("Software Project: %s", getName());
    }
}

