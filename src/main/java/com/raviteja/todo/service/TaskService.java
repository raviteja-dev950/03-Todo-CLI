package com.raviteja.todo.service;

import com.raviteja.todo.model.Task;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;
    private final String FILE_NAME = "tasks.txt";

    public TaskService() {
        loadFromFile(); // Load old tasks when app starts
    }

    // Add Task
    public Task addTask(String title) {
        Task task = new Task(nextId++, title);
        tasks.add(task);
        saveToFile();
        return task;
    }

    // View All
    public List<Task> getAllTasks() {
        return tasks;
    }

    // Mark Done using Stream API (Interview Favorite!)
    public boolean markDone(int id) {
        Task task = tasks.stream()
               .filter(t -> t.getId() == id)
               .findFirst()
               .orElse(null);
        if (task!= null) {
            task.setDone(true);
            saveToFile();
            return true;
        }
        return false;
    }

    // Delete using Stream API
    public boolean deleteTask(int id) {
        boolean removed = tasks.removeIf(t -> t.getId() == id);
        if (removed) saveToFile();
        return removed;
    }

    // Filter - Pending Tasks
    public List<Task> getPendingTasks() {
        return tasks.stream()
               .filter(t ->!t.isDone())
               .collect(Collectors.toList());
    }

    // Filter - Completed Tasks
    public List<Task> getCompletedTasks() {
        return tasks.stream()
               .filter(Task::isDone)
               .collect(Collectors.toList());
    }

    public int getTotalCount() {
        return tasks.size();
    }

    // --- File Handling - Data will not vanish! ---
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task t : tasks) {
                writer.println(t.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine())!= null) {
                String[] parts = line.split(",", 4);
                if (parts.length >= 3) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    boolean done = Boolean.parseBoolean(parts[2]);
                    Task task = new Task(id, title);
                    task.setDone(done);
                    tasks.add(task);
                    if (id >= nextId) nextId = id + 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}