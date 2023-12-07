package com.avow.task.manager.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskRequest {

    private String title;

    private String details;

    private boolean completed;
}
