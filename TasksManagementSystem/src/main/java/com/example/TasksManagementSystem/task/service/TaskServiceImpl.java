package com.example.TasksManagementSystem.task.service;

import com.example.TasksManagementSystem.task.exception.TaskNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.TasksManagementSystem.task.entity.Task;
import com.example.TasksManagementSystem.task.repository.TaskRepository;
import com.example.TasksManagementSystem.task.service.dto.TaskDTO;
import com.example.TasksManagementSystem.task.service.mapper.TaskMapper;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    private final TaskMapper mapper = TaskMapper.INSTANCE;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @CachePut(value = "tasks", key = "#result.id")
    @CacheEvict(value = "tasks", key = "'allTasks'")
    public TaskDTO save(TaskDTO taskDTO) {
        log.debug("Request to save task {}", taskDTO);
        Task task = mapper.toEntity(taskDTO);
        task = taskRepository.save(task);
        log.info("Task saved successfully {}", task);
        return mapper.toDTO(task);
    }

    @Override
    @CachePut(value = "tasks", key = "#id")
    @CacheEvict(value = "tasks", key = "'allTasks'")
    public TaskDTO update(Long id, TaskDTO updatedTask) {
        Task existingTask = getTask(id);

        if (updatedTask.getTitle() != null) {
            existingTask.setTitle(updatedTask.getTitle());
        }
        if (updatedTask.getDescription() != null) {
            existingTask.setDescription(updatedTask.getDescription());
        }
        if (updatedTask.getStatus() != null) {
            existingTask.setStatus(updatedTask.getStatus());
        }
        if (updatedTask.getStartTime() != null) {
            existingTask.setStartTime(updatedTask.getStartTime());
        }
        if (updatedTask.getEndTime() != null) {
            existingTask.setEndTime(updatedTask.getEndTime());
        }
        if (updatedTask.getGoogleEventId() != null) {
            existingTask.setGoogleEventId(updatedTask.getGoogleEventId());
        }
        if (updatedTask.getIsSynced() != null) {
            existingTask.setIsSynced(updatedTask.getIsSynced());
        }

        save(mapper.toDTO(existingTask));
        log.info("Task updated successfully with ID: {}", id);
        return mapper.toDTO(existingTask);
    }

    @Override
    @Cacheable(value = "tasks", key = "#id")
    @Transactional(readOnly = true)
    public TaskDTO findById(long id) {
        log.debug("Request to get task with ID: {}", id);
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return mapper.toDTO(existingTask);
    }

    @Override
    @Cacheable(value = "tasks")
    @Transactional(readOnly = true)
    public List<TaskDTO> findTasksByIsSynced(boolean isSynced) {
        log.debug("Request to get all tasks by isSynced {}", isSynced);
        return taskRepository.findAllByIsSynced(isSynced).stream().map(mapper::toDTO).toList();
    }

    @Override
    @Cacheable(value = "tasks", key = "'allTasks'")
    @Transactional(readOnly = true)
    public List<TaskDTO> findAll() {
        log.debug("Request to get all tasks");
        return taskRepository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "tasks", key = "#id"),
            @CacheEvict(value = "tasks", key = "'allTasks'")
    })
    public void delete(long id) {
        log.debug("Request to delete task with ID: {}", id);
        Task task = getTask(id);
        taskRepository.delete(task);
        log.info("Task deleted successfully with ID: {}", id);
    }

    @Override
    @CacheEvict(value = "tasks", key = "'allTasks'")
    public void deleteAll(List<Long> ids) {
        log.debug("Request to delete all tasks with IDs: {}", ids);
        for (long id : ids) {
            delete(id);
        }
        log.info("All tasks deleted successfully with IDs: {}", ids);
    }

    private Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

}
