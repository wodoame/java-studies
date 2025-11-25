package com.projectmanagement.utils;

import com.projectmanagement.models.*;
import com.projectmanagement.services.ProjectService;
import com.projectmanagement.services.ReportService;
import com.projectmanagement.services.TaskService;

import java.util.Scanner;

public class ConsoleMenu {
    private static final int MAX_PROJECTS = 10;
    private static final int MAX_TASKS_PER_PROJECT = 20;

    private final Scanner scanner = new Scanner(System.in);
    private final ProjectService projectService = new ProjectService(MAX_PROJECTS);
    private final TaskService taskService = new TaskService();
    private final ReportService reportService = new ReportService();

    private User currentUser;
    private int projectIdCounter = 1;
    private int taskIdCounter = 1;

    public ConsoleMenu() {
        seedSampleData();
    }

    public void start() {
        System.out.println("Welcome to the Project Management System");
        login();
        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = readInt("Select an option: ");
            switch (choice) {
                case 1 -> listProjects();
                case 2 -> createProject();
                case 3 -> manageTasks();
                case 4 -> viewReports();
                case 5 -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        System.out.println("Goodbye!");
    }

    private void showMainMenu() {
        System.out.println("\n==== Main Menu ====");
        System.out.println("1. View Projects");
        System.out.println("2. Create Project");
        System.out.println("3. Manage Tasks");
        System.out.println("4. View Status Reports");
        System.out.println("5. Exit");
    }

    private void login() {
        System.out.println("\nSelect user type:");
        System.out.println("1. Admin");
        System.out.println("2. Regular");
        int choice = readInt("Choice: ");
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        currentUser = choice == 1 ? new AdminUser(name, email) : new RegularUser(name, email);
        System.out.printf("Logged in as %s (%s)%n", currentUser.getName(), currentUser.getRole());
    }

    private void listProjects() {
        System.out.println("\n-- Projects --");
        Project[] projects = projectService.getProjects();
        for (int i = 0; i < projectService.getProjectCount(); i++) {
            projects[i].displayProject();
        }
    }

    private void createProject() {
        if (!currentUser.canModify()) {
            System.out.println("Only admins can create projects.");
            return;
        }
        System.out.println("\nChoose project type: 1.Software 2.Hardware");
        int typeChoice = readInt("Type: ");
        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        double budget = readDouble("Budget: ");
        int teamSize = readInt("Team size: ");

        Project project = typeChoice == 1
                ? new SoftwareProject(projectIdCounter++, name, description, budget, teamSize, MAX_TASKS_PER_PROJECT)
                : new HardwareProject(projectIdCounter++, name, description, budget, teamSize, MAX_TASKS_PER_PROJECT);
        if (projectService.addProject(project)) {
            System.out.println("Project created successfully.");
        } else {
            System.out.println("Project list full.");
        }
    }

    private void manageTasks() {
        if (projectService.getProjectCount() == 0) {
            System.out.println("No projects available.");
            return;
        }
        listProjects();
        int projectId = readInt("Enter project ID: ");
        Project project = projectService.findById(projectId);
        if (project == null) {
            System.out.println("Project not found.");
            return;
        }
        System.out.println("1. Add Task 2. Update Task 3. Delete Task");
        int choice = readInt("Choice: ");
        switch (choice) {
            case 1 -> addTask(project);
            case 2 -> updateTask(project);
            case 3 -> deleteTask(project);
            default -> System.out.println("Invalid task choice.");
        }
    }

    private void addTask(Project project) {
        scanner.nextLine();
        System.out.print("Task name: ");
        String name = scanner.nextLine();
        if (project.findTaskByName(name) != null) {
            System.out.println("Task name already exists.");
            return;
        }
        Task task = new Task(taskIdCounter++, name, Task.Status.PENDING);
        if (taskService.addTaskToProject(project, task)) {
            System.out.println("Task added.");
        } else {
            System.out.println("Task list full.");
        }
    }

    private void updateTask(Project project) {
        int taskId = readInt("Task ID: ");
        scanner.nextLine();
        System.out.print("New status (PENDING/IN_PROGRESS/COMPLETED): ");
        String statusInput = scanner.nextLine();
        if (!Task.isValidStatus(statusInput)) {
            System.out.println("Invalid status.");
            return;
        }
        Task.Status status = Task.Status.valueOf(statusInput.toUpperCase());
        if (taskService.updateTaskStatus(project, taskId, status)) {
            System.out.println("Task updated.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private void deleteTask(Project project) {
        if (!currentUser.canModify()) {
            System.out.println("Only admins can delete tasks.");
            return;
        }
        int taskId = readInt("Task ID: ");
        if (taskService.deleteTask(project, taskId)) {
            System.out.println("Task deleted.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private void viewReports() {
        if (projectService.getProjectCount() == 0) {
            System.out.println("No projects.");
            return;
        }
        reportService.displayAllReports(projectService.getProjects(), projectService.getProjectCount());
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid number. Try again: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid number. Try again: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private void seedSampleData() {
        Project sample = new SoftwareProject(projectIdCounter++, "Alpha", "Sample project", 10000, 5, MAX_TASKS_PER_PROJECT);
        projectService.addProject(sample);
        taskService.addTaskToProject(sample, new Task(taskIdCounter++, "Setup", Task.Status.COMPLETED));
        taskService.addTaskToProject(sample, new Task(taskIdCounter++, "Planning", Task.Status.IN_PROGRESS));
    }
}

