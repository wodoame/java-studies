package com.projectmanagement.models;

public abstract class Project {
    private final int id;
    private final String type;
    private String name;
    private String description;
    private double budget;
    private int teamSize;
    private final Task[] tasks;
    private int taskCount;

    protected Project(int id, String type, String name, String description, double budget, int teamSize, int maxTasks) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.budget = budget;
        this.teamSize = teamSize;
        this.tasks = new Task[maxTasks];
        this.taskCount = 0;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public boolean addTask(Task task) {
        if (taskCount >= tasks.length) {
            return false;
        }
        tasks[taskCount++] = task;
        return true;
    }

    public boolean removeTask(int taskId) {
        for (int i = 0; i < taskCount; i++) {
            if (tasks[i].getId() == taskId) {
                tasks[i] = tasks[taskCount - 1];
                tasks[taskCount - 1] = null;
                taskCount--;
                return true;
            }
        }
        return false;
    }

    public Task findTaskById(int id) {
        for (int i = 0; i < taskCount; i++) {
            if (tasks[i].getId() == id) {
                return tasks[i];
            }
        }
        return null;
    }

    public Task findTaskByName(String name) {
        for (int i = 0; i < taskCount; i++) {
            if (tasks[i].getName().equalsIgnoreCase(name)) {
                return tasks[i];
            }
        }
        return null;
    }

    public Task[] getTasksSnapshot() {
        Task[] snapshot = new Task[taskCount];
        System.arraycopy(tasks, 0, snapshot, 0, taskCount);
        return snapshot;
    }

    public int getMaxTasks() {
        return tasks.length;
    }

    public abstract String getProjectDetails();

    public void displayProject() {
        System.out.printf("[#%d] %s (%s)%nBudget: $%.2f | Team Size: %d%n%s%n", id, name, type, budget, teamSize, description);
    }
}
