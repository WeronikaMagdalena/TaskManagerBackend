package com.example.taskmanager;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testSaveTask() {
        Task task = new Task("Test Task", "Task Description", false, LocalDate.of(2024, 12, 31));
        Task savedTask = taskRepository.save(task);

        assertThat(savedTask.getId()).isNotNull();
        assertThat(savedTask.getTitle()).isEqualTo("Test Task");
        assertThat(savedTask.getDescription()).isEqualTo("Task Description");
        assertThat(savedTask.isCompleted()).isFalse();
        assertThat(savedTask.getDeadline()).isEqualTo(LocalDate.of(2024, 12, 31));
    }

    @Test
    void testFindTaskById() {
        Task task = new Task("Find Task", "Find Description", false, LocalDate.of(2025, 1, 1));
        Task savedTask = taskRepository.save(task);

        Optional<Task> retrievedTask = taskRepository.findById(savedTask.getId());
        assertThat(retrievedTask).isPresent();
        assertThat(retrievedTask.get().getTitle()).isEqualTo("Find Task");
    }

    @Test
    void testFindAllTasks() {
        Task task1 = new Task("Task 1", "Description 1", false, LocalDate.of(2024, 12, 31));
        Task task2 = new Task("Task 2", "Description 2", true, LocalDate.of(2025, 1, 1));
        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).hasSize(2);
    }

    @Test
    void testUpdateTask() {
        Task task = new Task("Original Task", "Original Description", false, LocalDate.of(2024, 12, 31));
        Task savedTask = taskRepository.save(task);

        savedTask.setTitle("Updated Task");
        savedTask.setCompleted(true);
        Task updatedTask = taskRepository.save(savedTask);

        assertThat(updatedTask.getTitle()).isEqualTo("Updated Task");
        assertThat(updatedTask.isCompleted()).isTrue();
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("Delete Task", "Delete Description", false, LocalDate.of(2025, 1, 1));
        Task savedTask = taskRepository.save(task);

        taskRepository.deleteById(savedTask.getId());
        Optional<Task> deletedTask = taskRepository.findById(savedTask.getId());

        assertThat(deletedTask).isEmpty();
    }
}

