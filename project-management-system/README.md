# Project Management System

Console-based task management lab built with core Java and arrays.

## Features
- Manage software and hardware projects with core metadata.
- Add, update, and delete tasks per project with status validation.
- Role-based access for admin/regular users.
- Completion reports with per-project summaries.

## Project Structure
```
src/
  com/projectmanagement/
    Main.java
    interfaces/
    models/
    services/
    utils/
resources/
  project-requirements.md
```

## Running the App
Ensure JDK 21+ is installed.

```bash
cd /home/bernard-mawulorm-kofi-wodoame/IdeaProjects/java-studies/project-management-system
javac $(find src -name "*.java")
java -cp src com.projectmanagement.Main
```

## Future Enhancements
1. Persist data to files or collections.
2. Add filtering options and richer reporting.
3. Create UML diagram and extended documentation per requirements.

