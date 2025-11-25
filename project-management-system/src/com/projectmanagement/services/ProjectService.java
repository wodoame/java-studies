package com.projectmanagement.services;

import com.projectmanagement.models.Project;

public class ProjectService {
    private final Project[] projects;
    private int projectCount;

    public ProjectService(int maxProjects) {
        this.projects = new Project[maxProjects];
        this.projectCount = 0;
    }

    public boolean addProject(Project project) {
        if (projectCount >= projects.length) {
            return false;
        }
        projects[projectCount++] = project;
        return true;
    }

    public Project[] getProjects() {
        return projects;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public Project findById(int id) {
        for (int i = 0; i < projectCount; i++) {
            if (projects[i].getId() == id) {
                return projects[i];
            }
        }
        return null;
    }
}

