package com.raviteja.todo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String title;
    private boolean isDone;
    private LocalDateTime createdAt;

    // Constructor
    public Task(int id, String title) {
        this.id = id;
        this.title = title;
        this.isDone = false;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public boolean isDone() { return isDone; }
    public void setDone(boolean done) { isDone = done; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Pretty Display
    @Override
    public String toString() {
        String status = isDone ? "✅ Done" : "⏳ Pending";
        String time = createdAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return String.format("ID: %d | %s | %s | Created: %s", id, title, status, time);
    }
    
    // For saving to file
    public String toFileString() {
        return id + "," + title + "," + isDone + "," + createdAt;
    }
}