package com.example.TasksManagementSystem.task.service.mapper;

import com.example.TasksManagementSystem.task.entity.Task;
import com.example.TasksManagementSystem.task.service.dto.TaskDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-14T19:08:50+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class TaskMapperImpl implements TaskMapper {

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
