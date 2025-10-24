package com.abhishek.todo.todoapp.Model;

import java.time.LocalDate;

public class Todo {
    private int id;
    private String title;
    private boolean completed;
    private LocalDate dueDate;

    public Todo() {}

    public Todo(int id, String title, boolean completed, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.dueDate = dueDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}
