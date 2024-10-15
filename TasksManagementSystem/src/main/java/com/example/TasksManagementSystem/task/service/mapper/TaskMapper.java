package com.example.TasksManagementSystem.task.service.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import com.example.TasksManagementSystem.task.entity.Task;
import com.example.TasksManagementSystem.task.service.dto.TaskDTO;


public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO toDTO(Task task);

    Task toEntity(TaskDTO taskDTO);
}
