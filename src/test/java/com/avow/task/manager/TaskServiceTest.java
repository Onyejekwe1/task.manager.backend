package com.avow.task.manager;

import com.avow.task.manager.contract.TaskRequest;
import com.avow.task.manager.contract.TaskResponse;
import com.avow.task.manager.entity.Task;
import com.avow.task.manager.exception.ResourceNotFoundException;
import com.avow.task.manager.repository.TaskRepository;
import com.avow.task.manager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask_success() {
        TaskRequest request = new TaskRequest("Task Title", "Task Details", false);
        Task task = new Task(1L, "Task Title", "Task Details", false);
        when(taskRepository.save(any(Task.class))).thenReturn(Mono.just(task));

        Mono<TaskResponse> result = taskService.createTask(request);

        StepVerifier.create(result)
                .expectNextMatches(response ->
                        response.getId().equals(1L) &&
                                response.getTitle().equals("Task Title") &&
                                response.getDetails().equals("Task Details") &&
                                !response.isCompleted())
                .verifyComplete();
    }

    @Test
    void getTaskById_existingTask() {
        Task task = new Task(1L, "Task Title", "Task Details", false);
        when(taskRepository.findById(1L)).thenReturn(Mono.just(task));

        Mono<TaskResponse> result = taskService.getTaskById(1L);

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getId().equals(1L))
                .verifyComplete();
    }


    @Test
    void getTaskById_nonExistingTask() {
        when(taskRepository.findById(1L)).thenReturn(Mono.empty());

        Mono<TaskResponse> result = taskService.getTaskById(1L);

        StepVerifier.create(result)
                .expectError(ResourceNotFoundException.class)
                .verify();
    }

    @Test
    void updateTask_success() {
        TaskRequest request = new TaskRequest("Updated Title", "Updated Details", true);
        Task existingTask = new Task(1L, "Task Title", "Task Details", false);
        Task updatedTask = new Task(1L, "Updated Title", "Updated Details", true);
        when(taskRepository.findById(1L)).thenReturn(Mono.just(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(Mono.just(updatedTask));

        Mono<TaskResponse> result = taskService.updateTask(1L, request);

        StepVerifier.create(result)
                .expectNextMatches(response ->
                        response.getTitle().equals("Updated Title") &&
                                response.getDetails().equals("Updated Details") &&
                                response.isCompleted())
                .verifyComplete();
    }


    @Test
    void deleteTask_success() {
        when(taskRepository.deleteById(1L)).thenReturn(Mono.empty());

        Mono<Void> result = taskService.deleteTask(1L);

        StepVerifier.create(result)
                .verifyComplete();
    }
}
