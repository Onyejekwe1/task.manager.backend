package com.avow.task.manager.repository;

import com.avow.task.manager.entity.Task;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Long> {
    // Custom query methods can be added here
}
