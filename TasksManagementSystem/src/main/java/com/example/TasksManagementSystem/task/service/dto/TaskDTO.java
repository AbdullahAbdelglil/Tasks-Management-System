package com.example.TasksManagementSystem.task.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;

    private String title;

    private String description;

    private String status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String googleEventId;

    private Boolean isSynced;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}

