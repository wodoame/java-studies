package com.projectmanagement.services;

import com.projectmanagement.models.Project;
import com.projectmanagement.models.StatusReport;
import com.projectmanagement.models.Task;

public class ReportService {
    private static final double PERCENT_MULTIPLIER = 100.0;

    public StatusReport generateStatusReport(Project project) {
        Task[] tasks = project.getTasksSnapshot();
        int total = tasks.length;
        int completed = 0;
        for (Task task : tasks) {
            if (task != null && task.isCompleted()) {
                completed++;
            }
        }

        double completion = total == 0 ? 0 : (completed * PERCENT_MULTIPLIER) / total;
        completion = Math.round(completion * 100.0) / 100.0;
        return new StatusReport(project, total, completed, completion);
    }

    public void displayAllReports(Project[] projects, int projectCount) {
        for (int i = 0; i < projectCount; i++) {
            generateStatusReport(projects[i]).display();
        }
    }
}

