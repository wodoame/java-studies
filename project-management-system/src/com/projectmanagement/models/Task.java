package com.projectmanagement.models;

import com.projectmanagement.interfaces.Completable;

public class Task implements Completable {
    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    private final int id;
    private String name;
    private Status status;

    public Task(int id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean isCompleted() {
        return status == Status.COMPLETED;
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d, name='%s', status=%s}", id, name, status);
    }

    public static boolean isValidStatus(String status) {
        for (Status value : Status.values()) {
            if (value.name().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }
}
