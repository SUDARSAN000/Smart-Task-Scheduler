# Smart Task Scheduler with Priority Queues

This project is a simple JavaFX-based Task Scheduler application that allows users to manage their daily tasks with an intuitive user interface and task prioritization based on urgency.

## Introduction

The Smart Task Scheduler is a desktop application built in Java using JavaFX. 
It provides users the ability to create, manage, and remove tasks, all organized by priority. 
The program uses Java’s `PriorityQueue` to ensure high-priority tasks are processed or displayed first. 
This project is designed for learning GUI development in Java, object-oriented programming, and basic task handling with collections.

## Abstract

The purpose of this project is to develop a lightweight task scheduler that prioritizes tasks using a priority queue.
Users can enter task details via the GUI, and tasks are stored and organized internally.
The application supports dynamic task creation, storage, and viewing. 
Though basic in structure, the app is scalable and could be extended with features like file-based persistence, task reminders, or calendar views.

## Tools Used

- Java (JDK 11+)
- JavaFX
- Eclipse IDE (or IntelliJ IDEA)
- Git & GitHub (for version control)

## Steps Involved in Building the Project

1. **Designing the Task Model**  
   A `Task` class was created to encapsulate task data such as title, description, and priority level.

2. **Creating the Storage Mechanism**  
   A `TaskStorage` class maintains an in-memory `PriorityQueue` for managing tasks by priority.

3. **Implementing the UI**  
   JavaFX was used to build the user interface. `TaskSchedulerUI.java` sets up the layout with fields for task input and buttons for user interaction.

4. **Handling Task Operations**  
   The `TaskController` handles the logic for adding tasks, updating the UI, and interacting with `TaskStorage`.

5. **Launching the Application**  
   The `Main.java` file is the entry point that launches the JavaFX application window.

## Project Structure

├── Main.java # Launches the JavaFX application
├── Task.java # Model class for storing task data
├── TaskStorage.java # In-memory storage using PriorityQueue
├── TaskSchedulerUI.java # UI layout and components
├── TaskController.java # Event handling and logic controller


## Conclusion

This project demonstrates how to use JavaFX for GUI development, implement priority-based logic using `PriorityQueue`, and apply object-oriented principles in Java. 
While currently using in-memory storage, the architecture can easily be extended with persistent file storage, alerts, or more advanced scheduling features.

## How to Run

1. Download and install Java JDK 11+.
2. Install JavaFX SDK from https://gluonhq.com/products/javafx/
3. Compile and run using the following commands:

```bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls *.java
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls Main
