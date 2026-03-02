# TaskFlow Documentation
---

## Getting Started Guide

### Overview

TaskFlow is a web-based task management application that helps users organize, track, and complete daily tasks efficiently. It provides a simple interface for creating, updating, and managing tasks.

---

### Creating an Account

1. Navigate to the TaskFlow homepage
2. Click **Sign Up**
3. Enter your email and password
4. Click **Create Account**

You will be redirected to your dashboard after successful registration.

---

### Logging In

1. Click **Login** on the homepage
2. Enter your email and password
3. Click **Submit**

If your credentials are correct, you will be logged into your account.

---

### Dashboard Overview

After logging in, you will see:

* A list of your tasks
* A button to create new tasks
* Options to edit or delete tasks

---

### Creating a Task

1. Click **Add Task**
2. Enter:

   * Title
   * Description
   * Due date (optional)
3. Click **Save**

---

### Managing Tasks

* **Edit Task:** Click the *Edit* button and update the details
* **Delete Task:** Click the *Delete* button
* **Mark as Complete:** Toggle the completion checkbox

---

## API Reference

### Base URL

```
https://api.taskflow.com
```

---

### Authentication

All protected routes require a JWT token:

```
Authorization: Bearer <token>
```

---

### POST /auth/register

Creates a new user account.

**Request Body**

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response (201)**

```json
{
  "message": "User created successfully"
}
```

---

### POST /auth/login

Authenticates a user and returns a token.

**Request Body**

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response (200)**

```json
{
  "token": "jwt_token_here"
}
```

**Response (401)**

```json
{
  "error": "Invalid credentials"
}
```

---

### GET /tasks

Returns all tasks for the authenticated user.

**Response (200)**

```json
[
  {
    "id": 1,
    "title": "Finish assignment",
    "description": "Complete AI lab",
    "completed": false,
    "dueDate": "2026-02-25"
  }
]
```

---

### POST /tasks

Creates a new task.

**Request Body**

```json
{
  "title": "Study APIs",
  "description": "Review REST concepts",
  "dueDate": "2026-02-24"
}
```

**Response (201)**

```json
{
  "id": 2,
  "message": "Task created successfully"
}
```

---

### PUT /tasks/{id}

Updates an existing task.

**Request Body**

```json
{
  "title": "Study APIs (updated)",
  "completed": true
}
```

**Response (200)**

```json
{
  "message": "Task updated successfully"
}
```

---

### DELETE /tasks/{id}

Deletes a task.

**Response (200)**

```json
{
  "message": "Task deleted successfully"
}
```

---

## Troubleshooting

### Login Fails

* Ensure your email and password are correct
* Check if Caps Lock is enabled
* Try resetting your password

---

### Unauthorized (401 Error)

* Your session may have expired
* Log in again to get a new token

---

### Tasks Not Saving

* Check your internet connection
* Ensure all required fields are filled
* Refresh the page and try again

---

### Tasks Not Updating

* Confirm you are editing the correct task
* Ensure the task ID exists
* Retry the request

---

### Server Error (500)

* This may be a temporary issue
* Try again later
* Contact support if the issue persists

---

## Prompt History

### Prompt 1 (Initial – Generic)

> Write a getting started guide, API reference, and troubleshooting section for a task management app.

**Issue:** Output was too generic and lacked structure.

---

### Prompt 2 (Refined – Context Added)

> Act as a technical writer. Create documentation for a web app called TaskFlow that allows users to register, log in, and manage tasks. Include a getting started guide, API reference, and troubleshooting section.

**Improvement:** More relevant content, but still inconsistent formatting.

---

### Prompt 3 (Structured Output)

> Rewrite the documentation using consistent formatting. Use H2 for main sections and H3 for sub-sections. Ensure the language is simple and clear for non-technical users.

**Improvement:** Better readability and structure.

---

### Prompt 4 (API Enhancement)

> Generate a realistic REST API with endpoints for authentication and task management. Include request/response examples and status codes.

**Improvement:** API became detailed and realistic.

---

### Prompt 5 (Final Refinement)

> Review the entire document for clarity, consistency, and completeness. Ensure all sections follow the same tone and formatting.

**Improvement:** Final polished version.
---

## Reflection 

The most challenging part of this task was ensuring that the AI-generated content was both accurate and well-structured. Initial outputs were often too generic and lacked consistency across sections. This required careful review and multiple refinements.

Iterative prompting significantly improved the quality of the documentation. By gradually adding more context and specific instructions, the responses became clearer, more detailed, and better aligned with the requirements. Chaining prompts was especially useful in standardizing tone and formatting across all sections.

This process demonstrated that while AI can quickly generate content, achieving high-quality results requires active guidance and refinement. The combination of iteration and critical review was essential in producing a clear and professional final document.


---
