package com.example.TasksManagementSystem.task.service.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskDTO implements Serializable {
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

