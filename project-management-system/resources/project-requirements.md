## Task Management System Overview

[cite\_start]This document outlines the requirements and structure for building a console-based Project/Task Management System using core Java and Object-Oriented Principles[cite: 8, 9].

-----

### 🚀 Project Summary

| Detail | Value |
| :--- | :--- |
| **Complexity** | [cite\_start]Medium [cite: 5] |
| **Time Estimate** | [cite\_start]10 hours [cite: 6] |
| **Technology Stack** | [cite\_start]Java 21 (LTS), IntelliJ IDEA Community Edition [cite: 7] |
| **Data Storage** | [cite\_start]In-memory using arrays (no databases or external dependencies) [cite: 9] |
| **Goal** | [cite\_start]Build a console-based system to create projects, add/assign tasks, record completion status, and calculate completion averages[cite: 8]. |

### 📚 Learning Objectives

By completing this lab, you will be able to:

  * [cite\_start]Design a simple console-based application using core Java[cite: 16].
  * [cite\_start]Apply object-oriented programming principles (encapsulation, inheritance, and polymorphism) to represent projects, tasks, and users[cite: 17].
  * [cite\_start]Use Java arrays, loops, and conditional structures to manage data in memory[cite: 18].
  * [cite\_start]Define and extend classes, abstract classes, and interfaces to promote clean, reusable designs[cite: 19].
  * [cite\_start]Demonstrate method overloading and method overriding to implement flexible and polymorphic behaviors[cite: 20].
  * [cite\_start]Design simple menu-driven console interfaces with structured user input validation[cite: 21].
  * [cite\_start]Compute project completion percentages by aggregating data from arrays of task statuses[cite: 22].
  * [cite\_start]Lay the groundwork for future enhancements (e.g., using Java Collections and file-based data storage)[cite: 23, 11, 13].

-----

### ✨ System Features Overview

[cite\_start]The system must support the following five core features[cite: 25]:

| Feature | Description |
| :--- | :--- |
| **Feature 1: Project Catalog Management** | [cite\_start]Create new projects (e.g., Software Project, Hardware Project)[cite: 27]. [cite\_start]View all existing projects with details (ID, name, description, team size, budget)[cite: 28]. [cite\_start]Filter projects by type or search by budget range[cite: 29, 77]. |
| **Feature 2: Task Operations** | [cite\_start]Add tasks to specific projects[cite: 32]. [cite\_start]Assign task status (Pending, In Progress, Completed)[cite: 33]. [cite\_start]View all tasks per project, and update or delete tasks[cite: 34, 35]. [cite\_start]Validate inputs to prevent invalid task status or duplicate task names[cite: 36]. |
| **Feature 3: User Management** | [cite\_start]Create and manage system users (`RegularUser` and `AdminUser`)[cite: 38]. [cite\_start]Enforce role-based access (Admin can delete/update; Regular can view/add)[cite: 40]. [cite\_start]Automatically generate unique user IDs[cite: 40]. |
| **Feature 4: Status Processing & Reporting** | [cite\_start]Calculate and display completion averages per project[cite: 42]. [cite\_start]Generate status reports (e.g., "Project Alpha is 75% complete")[cite: 42]. [cite\_start]Display task statistics (total, completed, and pending counts)[cite: 43]. |
| **Feature 5: Menu Navigation & User Experience** | [cite\_start]Display a clear main menu and sub-menus[cite: 45]. [cite\_start]Validate all user inputs (numbers, text, IDs)[cite: 45]. [cite\_start]Provide formatted outputs and support graceful exit/return navigation[cite: 46]. |

-----

### 📝 Expected User Workflows

The system should support workflows like:

1.  [cite\_start]**Complete Task Assignment Journey:** User logs in, manages projects, adds a new project, adds tasks, updates task statuses, and views the calculated completion average and report [cite: 193-201].
2.  [cite\_start]**Project Discovery:** User browses projects, filters by type (e.g., Software), views details, adds a new related task, and returns to the main menu [cite: 202-207].
3.  [cite\_start]**Task Management:** User views tasks for a selected project, updates status, deletes outdated tasks, and the system recalculates progress [cite: 208-213].
4.  [cite\_start]**Status Reporting:** User opens "View Status Reports," the system displays all projects with completion percentages, and the user compares progress [cite: 214-217].

-----

### 👤 User Stories & Technical Requirements

#### Epic 1: Project Catalog Management

  * [cite\_start]**US-1.1: Browse Projects** [cite: 220]
      * [cite\_start]**Acceptance Criteria:** Display all projects with ID, name, type, budget, team size, and description[cite: 225, 226]. [cite\_start]Support filtering by project type[cite: 227].
      * [cite\_start]**Technical Requirements:** Create **abstract class `Project`** with private fields (`id`, `name`, `description`, `budget`, `teamSize`) [cite: 229, 230][cite\_start], abstract method `getProjectDetails()`, and concrete method `displayProject()`[cite: 231, 232]. [cite\_start]Implement subclasses **`SoftwareProject`** and **`HardwareProject`**[cite: 233]. [cite\_start]Store projects in an array (minimum 5)[cite: 234, 380].
  * [cite\_start]**US-1.2: Search Projects by Budget Range** [cite: 235]
      * [cite\_start]**Acceptance Criteria:** Input min and max budget, show projects within range, and validate numeric inputs[cite: 240, 241, 242].
      * [cite\_start]**Technical Requirements:** Use comparison operators, loops, and conditionals[cite: 244, 245]. [cite\_start]Display formatted output using `System.out.printf()`[cite: 246].

#### Epic 2: Task Operations

  * [cite\_start]**US-2.1: Add Task to Project** [cite: 248]
      * [cite\_start]**Acceptance Criteria:** Assign unique task IDs[cite: 253]. [cite\_start]Specify name and initial status[cite: 254]. [cite\_start]Prevent duplicates[cite: 255].
      * [cite\_start]**Technical Requirements:** Create **`Task` class** with fields (`id`, `name`, `status`)[cite: 257]. [cite\_start]Implement **interface `Completable`** with method `boolean isCompleted()`[cite: 258]. [cite\_start]Store tasks in an array within each project[cite: 259].
  * [cite\_start]**US-2.2: Update Task Status** [cite: 260]
      * [cite\_start]**Acceptance Criteria:** Select task by ID, choose from allowed statuses, update, and display a success message[cite: 265, 266, 267].
      * [cite\_start]**Technical Requirements:** Use loops to locate the task[cite: 269]. [cite\_start]Use enums or constants for valid statuses[cite: 270]. [cite\_start]Apply encapsulation with proper setters/getters[cite: 271, 382].

#### Epic 3: User Management

  * [cite\_start]**US-3.1: Create User Profiles** [cite: 273]
      * [cite\_start]**Acceptance Criteria:** Create `RegularUser` and `AdminUser` classes[cite: 278]. [cite\_start]Assign auto-generated unique IDs[cite: 279]. [cite\_start]Display user role when logged in[cite: 280].
      * [cite\_start]**Technical Requirements:** **Abstract `User` class** with fields (`id`, `name`, `email`, `role`)[cite: 282]. [cite\_start]Use static counter for ID generation[cite: 283]. [cite\_start]Apply inheritance and polymorphism for role behavior[cite: 284, 384].

#### Epic 4: Status Processing & Reporting

  * [cite\_start]**US-4.1: Calculate Project Completion Average** [cite: 286]
      * [cite\_start]**Acceptance Criteria:** Display total tasks, completed tasks, and percentage[cite: 291]. [cite\_start]Handle projects with zero tasks[cite: 292]. [cite\_start]**Round percentages to 2 decimals**[cite: 293].
      * [cite\_start]**Technical Requirements:** Use arithmetic and conditional operators[cite: 295]. [cite\_start]Iterate task arrays to count completions[cite: 296].

#### Epic 5: Menu Navigation & Application Control

  * [cite\_start]**US-5.1: Display Main Menu** [cite: 299]
      * [cite\_start]**Acceptance Criteria:** Display numbered menu options (1-5)[cite: 304]. [cite\_start]Validate choice[cite: 305]. [cite\_start]Loop until exit chosen[cite: 306].
      * [cite\_start]**Technical Requirements:** Use `Scanner` for input[cite: 308]. [cite\_start]Implement menu with `switch` or `if-else`[cite: 309]. [cite\_start]Wrap in a `do-while` loop for continuous running[cite: 310].

-----

### 📂 Project Structure

```
project-management-system/
└── src/
    [cite_start]├── Main.java              # Application entry point [cite: 317, 330]
    ├── models/
    [cite_start]│   ├── Project.java       # Abstract base class [cite: 318, 331]
    [cite_start]│   ├── SoftwareProject.java # Concrete project type [cite: 319, 331]
    [cite_start]│   ├── HardwareProject.java # Concrete project type [cite: 320, 331]
    [cite_start]│   ├── Task.java          # Task model [cite: 321, 331]
    [cite_start]│   ├── User.java          # Abstract user base [cite: 325, 332]
    [cite_start]│   ├── RegularUser.java   # Concrete user type [cite: 327, 332]
    [cite_start]│   ├── AdminUser.java     # Concrete user type [cite: 329, 332]
    [cite_start]│   └── StatusReport.java  # Status report generation [cite: 334]
    ├── interfaces/
    [cite_start]│   └── Completable.java   # Interface for completion logic [cite: 334]
    ├── services/
    [cite_start]│   ├── ProjectService.java  # Project operations [cite: 334]
    [cite_start]│   ├── TaskService.java     # Task operations [cite: 334]
    [cite_start]│   └── ReportService.java   # Reporting logic [cite: 334]
    └── utils/
        [cite_start]├── ConsoleMenu.java   # Menu handling [cite: 334]
        [cite_start]└── ValidationUtils.java # Input validation [cite: 334]
```

-----

### ⏱️ Implementation Phases

| Phase | Time Estimate | Tasks | Deliverables |
| :--- | :--- | :--- | :--- |
| **1: Foundation Setup** | [cite\_start]1-2 hours [cite: 337] | [cite\_start]Install JDK 21/IntelliJ, create structure, implement `Project` and `User` base classes, test sample data [cite: 339-342]. | [cite\_start]Working environment, abstract base classes, sample data printed [cite: 344-346]. |
| **2: Core Logic Development** | [cite\_start]2-3 hours [cite: 347] | [cite\_start]Implement `Task` and `Completable`[cite: 349]. [cite\_start]Add project subclasses[cite: 350]. [cite\_start]Implement task addition, updates, and completion calculation[cite: 351]. | [cite\_start]Working project-task relationship, functioning completion logic[cite: 353, 354]. |
| **3: User Interface Integration**| [cite\_start]2-3 hours [cite: 355] | [cite\_start]Implement console menu/navigation[cite: 357]. [cite\_start]Handle user inputs and validations[cite: 358]. [cite\_start]Display formatted lists and integrate reporting logic[cite: 359, 360]. | [cite\_start]Fully interactive console app, all user stories functional[cite: 362, 363]. |
| **4: Documentation & Finalization**| [cite\_start]1 hour [cite: 364] | [cite\_start]Create `README.md`[cite: 366]. [cite\_start]Add class diagram and OOP design rationale[cite: 367]. [cite\_start]Final testing and code cleanup[cite: 368]. | [cite\_start]Complete documentation, clean and well-commented codebase[cite: 370, 371]. |

-----

### ✅ Minimum Requirements Checklist

  * [cite\_start]☐ JDK 21 and IntelliJ installed [cite: 373]
  * [cite\_start]☐ At least 2 project types implemented [cite: 378]
  * [cite\_start]☐ At least 2 user types implemented [cite: 379]
  * [cite\_start]☐ Minimum 5 sample projects created [cite: 380]
  * [cite\_start]☐ Use of arrays for storage [cite: 381]
  * [cite\_start]☐ Encapsulation applied to all models [cite: 382]
  * [cite\_start]☐ Abstract classes & interfaces implemented [cite: 383]
  * [cite\_start]☐ Polymorphism demonstrated [cite: 384]
  * [cite\_start]☐ Input validation implemented [cite: 388]
  * [cite\_start]☐ Completion percentage calculation working [cite: 389]
  * [cite\_start]☐ Console navigation functional [cite: 390]
  * [cite\_start]☐ README and documentation included [cite: 391]

-----

### 📋 Submission Requirements

[cite\_start]**Required Deliverables**[cite: 395]:

  * Public GitHub Repository containing:
      * [cite\_start]All source code under `/src` [cite: 397]
      * [cite\_start]`README.md` with setup and usage guide [cite: 398, 401]
      * [cite\_start]UML diagram and design rationale [cite: 399, 403, 404]
      * [cite\_start]Feature summary mapped to user stories [cite: 402]

-----

Would you like to review the specific acceptance criteria for a particular User Story, such as calculating the Project Completion Average?