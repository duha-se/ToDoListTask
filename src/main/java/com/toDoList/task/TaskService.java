package com.toDoList.task;

import com.toDoList.task.dto.TaskCreationRequest;
import com.toDoList.task.dto.TaskUpdateRequest;
import com.toDoList.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.ValidationException;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Create a new task
    public Task createTask(TaskCreationRequest request) {

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setStatus(Status.PENDING); // Default status
        return taskRepository.save(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: ", id));
    }


    // Update a task (title, description, dueDate)
    public Task updateTask(Long id, TaskUpdateRequest request) {
        Task task = getTaskById(id);

        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }
        if (request.getDueDate() != null) {
            if (request.getDueDate().isBefore(LocalDate.now())) {
                throw new ValidationException("Due date must be a valid future date and in format dd-mm-yyyy");
            }
            task.setDueDate(request.getDueDate());
        }


        return taskRepository.save(task);
    }

    // Update task status
    public Task updateTaskStatus(Long id, Status status) {
        Task task = getTaskById(id);
        task.setStatus(status);
        return taskRepository.save(task);
    }

    // Delete a task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Filter tasks by status
    public List<Task> filterTasksByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    // Get overdue tasks
    public List<Task> getOverdueTasks() {
        return taskRepository.findByStatusAndDueDateBefore(Status.PENDING, LocalDate.now());
    }
}
