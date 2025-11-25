package com.projectmanagement.models;

public class HardwareProject extends Project {
    public HardwareProject(int id, String name, String description, double budget, int teamSize, int maxTasks) {
        super(id, "Hardware", name, description, budget, teamSize, maxTasks);
    }

    @Override
    public String getProjectDetails() {
        return String.format("Hardware Project: %s", getName());
    }
}

