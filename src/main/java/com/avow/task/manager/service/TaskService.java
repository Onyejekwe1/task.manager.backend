package com.avow.task.manager.service;

import com.avow.task.manager.contract.TaskRequest;
import com.avow.task.manager.contract.TaskResponse;
import com.avow.task.manager.entity.Task;
import com.avow.task.manager.exception.ResourceNotFoundException;
import com.avow.task.manager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Flux<TaskResponse> getAllTasks() {
        return taskRepository.findAll().map(this::toResponse);
    }

    public Mono<TaskResponse> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(this::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Task not found with id: " + id)));
    }

    public Mono<TaskResponse> createTask(TaskRequest taskDto) {
        return taskRepository.save(Task.of(taskDto)).map(this::toResponse);
    }

    public Mono<TaskResponse> updateTask(Long id, TaskRequest taskDto) {
        return taskRepository.findById(id)
                .flatMap(existingTask -> {
                    Task updatedTask = Task.of(taskDto).toBuilder().id(id).build(); // Update task using toBuilder
                    return taskRepository.save(updatedTask);
                })
                .map(this::toResponse);
    }

    public Mono<Void> deleteTask(Long id) {
        return taskRepository.deleteById(id);
    }


    private TaskResponse toResponse(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.getDetails(), task.isCompleted());
    }
}
