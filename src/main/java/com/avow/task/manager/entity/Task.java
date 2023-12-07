package com.avow.task.manager.entity;

import com.avow.task.manager.contract.TaskRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class Task {

    @Id
    private Long id;

    private String title;

    private String details;

    private boolean completed;

    public static Task of(TaskRequest taskDto) {
        return Task.builder()
                //.id(taskDto.getId())
                .completed(taskDto.isCompleted())
                .title(taskDto.getTitle())
                .details(taskDto.getDetails())
                .build();
    }
}
