package com.projectmanagement.models;

public class StatusReport {
    private final Project project;
    private final int totalTasks;
    private final int completedTasks;
    private final double completionPercentage;

    public StatusReport(Project project, int totalTasks, int completedTasks, double completionPercentage) {
        this.project = project;
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.completionPercentage = completionPercentage;
    }

    public void display() {
        System.out.printf("Project: %s%nTotal Tasks: %d, Completed: %d, Completion: %.2f%%%n", project.getName(), totalTasks, completedTasks, completionPercentage);
    }
}

