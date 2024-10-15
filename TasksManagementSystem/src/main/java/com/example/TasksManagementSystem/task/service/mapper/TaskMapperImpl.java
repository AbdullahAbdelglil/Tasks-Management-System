package com.example.TasksManagementSystem.task.service.mapper;

import com.example.TasksManagementSystem.task.entity.Task;
import com.example.TasksManagementSystem.task.service.dto.TaskDTO;

public class TaskMapperImpl implements TaskMapper{
    @Override
    public TaskDTO toDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId( task.getId() );
        taskDTO.setTitle( task.getTitle() );
        taskDTO.setDescription( task.getDescription() );
        taskDTO.setStatus( task.getStatus() );
        taskDTO.setStartTime( task.getStartTime() );
        taskDTO.setEndTime( task.getEndTime() );
        taskDTO.setGoogleEventId( task.getGoogleEventId() );
        taskDTO.setIsSynced( task.getIsSynced() );
        taskDTO.setCreatedAt( task.getCreatedAt() );
        taskDTO.setUpdatedAt( task.getUpdatedAt() );

        return taskDTO;
    }

    @Override
    public Task toEntity(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDTO.getId() );
        task.setTitle( taskDTO.getTitle() );
        task.setDescription( taskDTO.getDescription() );
        task.setStatus( taskDTO.getStatus() );
        task.setStartTime( taskDTO.getStartTime() );
        task.setEndTime( taskDTO.getEndTime() );
        task.setGoogleEventId( taskDTO.getGoogleEventId() );
        task.setIsSynced( taskDTO.getIsSynced() );
        task.setCreatedAt( taskDTO.getCreatedAt() );
        task.setUpdatedAt( taskDTO.getUpdatedAt() );

        return task;
    }
}
