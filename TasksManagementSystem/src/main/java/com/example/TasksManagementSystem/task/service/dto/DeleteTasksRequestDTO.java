package com.example.TasksManagementSystem.task.service.dto;

import java.io.Serializable;
import java.util.List;

public class DeleteTasksRequestDTO implements Serializable {
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
