package com.example.TasksManagementSystem.task.service;

import com.example.TasksManagementSystem.task.service.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO save(TaskDTO task);

    TaskDTO update(Long id, TaskDTO task);

    TaskDTO findById(long id);

    List<TaskDTO> findTasksByIsSynced(boolean isSynced);

    List<TaskDTO> findAll();

    boolean delete(long id);

    void deleteAll(List<Long> ids);

}
