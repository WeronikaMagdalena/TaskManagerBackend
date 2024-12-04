package com.example.taskmanager;

import com.example.taskmanager.model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testTaskConstructorAndGetters() {
        LocalDate deadline = LocalDate.of(2024, 12, 31);
        Task task = new Task("Test Title", "Test Description", false, deadline);

        assertEquals("Test Title", task.getTitle());
        assertEquals("Test Description", task.getDescription());
        assertFalse(task.isCompleted());
        assertEquals(deadline, task.getDeadline());
    }

    @Test
    void testSetters() {
        Task task = new Task();

        task.setTitle("New Title");
        task.setDescription("New Description");
        task.setCompleted(true);
        LocalDate newDeadline = LocalDate.of(2025, 1, 1);
        task.setDeadline(newDeadline);

        assertEquals("New Title", task.getTitle());
        assertEquals("New Description", task.getDescription());
        assertTrue(task.isCompleted());
        assertEquals(newDeadline, task.getDeadline());
    }

    @Test
    void testToString() {
        LocalDate deadline = LocalDate.of(2024, 12, 31);
        Task task = new Task("Test Title", "Test Description", false, deadline);

        String expected = "Task{id=null, title='Test Title', description='Test Description', completed=false, deadline=2024-12-31}";
        assertEquals(expected, task.toString());
    }

    @Test
    void testId() {
        Task task = new Task();
        task.setId(1L);

        assertEquals(1L, task.getId());
    }
}
