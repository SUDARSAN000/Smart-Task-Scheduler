package view;

import java.util.List;


import controller.TaskController;
import model.Task;
import util.TaskStorage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskSchedulerUI extends Application {
    private final TaskController controller = new TaskController();
    private final ListView<String> taskListView = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        List<Task> loadedTasks = TaskStorage.loadTasks();
        loadedTasks.forEach(controller::addTask);
        updateTaskList(controller.getAllTasks());

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        TextField titleField = new TextField();
        titleField.setPromptText("Task Title");

        TextField descField = new TextField();
        descField.setPromptText("Task Description");

        ComboBox<String> priorityBox = new ComboBox<>();
        priorityBox.getItems().addAll("High (1)", "Medium (2)", "Low (3)");
        priorityBox.setValue("Medium (2)");

        TextField deadlineField = new TextField();
        deadlineField.setPromptText("Deadline (yyyy-MM-dd HH:mm)");

        Button addBtn = new Button("Add Task");

        HBox inputRow = new HBox(10, titleField, descField, priorityBox, deadlineField, addBtn);
        inputRow.setPadding(new Insets(5));

        Button allBtn = new Button("All Tasks");
        Button todayBtn = new Button("Today's Tasks");
        Button highBtn = new Button("High Priority");
        Button deleteBtn = new Button("Delete Selected");

        HBox buttonRow = new HBox(10, allBtn, todayBtn, highBtn, deleteBtn);
        buttonRow.setPadding(new Insets(5));

        root.getChildren().addAll(inputRow, buttonRow, taskListView);

        addBtn.setOnAction(e -> {
            try {
                String title = titleField.getText().trim();
                String desc = descField.getText().trim();
                int priority = Integer.parseInt(priorityBox.getValue().replaceAll("\\D", ""));
                LocalDateTime deadline = LocalDateTime.parse(deadlineField.getText().trim(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                Task task = new Task(title, desc, priority, deadline);
                controller.addTask(task);
                updateTaskList(controller.getAllTasks());

                titleField.clear();
                descField.clear();
                deadlineField.clear();
            } catch (Exception ex) {
                showAlert("Invalid Input", "Please check your fields: " + ex.getMessage());
            }
        });

        allBtn.setOnAction(e -> updateTaskList(controller.getAllTasks()));
        todayBtn.setOnAction(e -> updateTaskList(controller.getTodayTasks()));
        highBtn.setOnAction(e -> updateTaskList(controller.getHighPriorityTasks()));

        deleteBtn.setOnAction(e -> {
            String selected = taskListView.getSelectionModel().getSelectedItem();
            if (selected != null && !selected.isBlank()) {
                String title = selected.split("]")[0].replace("[", "").trim();
                controller.deleteTask(title);
                updateTaskList(controller.getAllTasks());
            }
        });

        primaryStage.setOnCloseRequest(e -> {
            TaskStorage.saveTask(controller.getAllTasks());
        });

        primaryStage.setTitle("Smart Task Scheduler");
        primaryStage.setScene(new Scene(root, 1000, 400));
        primaryStage.show();
    }

    private void updateTaskList(List<Task> tasks) {
        taskListView.getItems().clear();
        tasks.forEach(t -> taskListView.getItems().add(t.toString()));
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
