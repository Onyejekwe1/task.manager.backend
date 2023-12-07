package com.avow.task.manager.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskResponse extends TaskRequest {
        private Long id;

        public TaskResponse(Long id, String title, String details, boolean completed) {
                super(title, details, completed);
                this.id = id;
        }
}
