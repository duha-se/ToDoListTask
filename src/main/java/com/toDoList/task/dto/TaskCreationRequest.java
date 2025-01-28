package com.toDoList.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskCreationRequest {

    @NotNull(message = "Title must not be null or empty.")
    @Size(max = 255, message = "Title must not exceed 255 characters.")
    private String title;

    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @FutureOrPresent(message = "Due date must be a valid future or present date.")
    private LocalDate dueDate;

    private String status;

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
