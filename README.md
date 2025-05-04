# Student Course Enrollment Management System

## 📘 Project Overview

This project is a simple **Java application** that manages users, students, courses, and their enrollments using a **SQLite database**. It demonstrates basic operations like adding records, updating data, deleting entries, and enforcing constraints such as foreign keys and field validation via JDBC.

---

## 🛠️ Setup & Running the Project

Follow these steps to set up and run the project in a development environment using **Visual Studio Code**:

### 1. Install Required Software
- **Java Development Kit (JDK)** – Download and install [JDK 17+](https://jdk.java.net/)
- **Visual Studio Code** – [Download here](https://code.visualstudio.com/)
- **Extension Pack for Java** – Install it from the VSCode Extensions Marketplace

### 2. Configure SQLite JDBC Driver
- Download the SQLite JDBC driver (version `3.49.1.0`) from [this GitHub release page](https://github.com/xerial/sqlite-jdbc/releases)
- Create a `lib/` folder in your project root
- Place the downloaded `.jar` file (`sqlite-jdbc-3.49.1.0.jar`) into the `lib` folder

### 3. Configure VS Code Project Settings
Make sure the following content exists in `.vscode/settings.json`:

```json
{
    "java.project.sourcePaths": [
        "src"
    ],
    "java.project.outputPath": "bin",
    "java.project.referencedLibraries": [
        "lib/sqlite-jdbc-3.49.1.0.jar"
    ]
}

