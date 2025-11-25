package com.projectmanagement.services;

import com.projectmanagement.models.Project;
import com.projectmanagement.models.Task;

public class TaskService {
    public boolean addTaskToProject(Project project, Task task) {
        if (project.findTaskByName(task.getName()) != null) {
            return false;
        }
        return project.addTask(task);
    }

    public boolean updateTaskStatus(Project project, int taskId, Task.Status status) {
        Task task = project.findTaskById(taskId);
        if (task == null) {
            return false;
        }
        task.setStatus(status);
        return true;
    }

    public boolean deleteTask(Project project, int taskId) {
        return project.removeTask(taskId);
    }
}

