package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTaskCompletion(
            @PathVariable Long id,
            @RequestBody Task taskDetails) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        // Update only the completed field
        task.setCompleted(taskDetails.isCompleted());
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
//        Task updatedTask = taskService.updateTask(id, taskDetails);
//        return updatedTask != null ? ResponseEntity.ok(updatedTask) : ResponseEntity.notFound().build();
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
//        taskService.deleteTask(id);
//        return ResponseEntity.noContent().build();
//    }
}

