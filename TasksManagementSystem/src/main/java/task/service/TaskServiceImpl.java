package task.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.entity.Task;
import task.repository.TaskRepository;
import task.service.dto.TaskDTO;
import task.service.mapper.TaskMapper;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;
    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        log.debug("Request to save task {}", taskDTO);
        Task task = TaskMapper.INSTANCE.toEntity(taskDTO);
        task = taskRepository.save(task);
        log.info("Task saved successfully {}", task);
        return TaskMapper.INSTANCE.toDTO(task);
    }

    @Override
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

        taskRepository.save(existingTask);
        log.info("Task updated successfully with ID: {}", id);
        return TaskMapper.INSTANCE.toDTO(existingTask);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDTO findById(long id) {
        log.debug("Request to get task with ID: {}", id);
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        return TaskMapper.INSTANCE.toDTO(existingTask);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findTasksByIsSynced(boolean isSynced) {
        log.debug("Request to get all tasks by isSynced {}", isSynced);
        return taskRepository.findAllByIsSynced(isSynced).stream().map(TaskMapper.INSTANCE::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAll() {
        log.debug("Request to get all tasks");
        return taskRepository.findAll().stream().map(TaskMapper.INSTANCE::toDTO).toList();
    }

    @Override
    public boolean delete(long id) {
        log.debug("Request to delete task with ID: {}", id);
        Task task = getTask(id);
        taskRepository.delete(task);
        log.info("Task deleted successfully with ID: {}", id);
        return true;
    }

    @Override
    public void deleteAll(List<Long> ids) {
        log.debug("Request to delete all tasks with IDs: {}", ids);
        for(long id: ids) {
            delete(id);
        }
        log.info("All tasks deleted successfully with IDs: {}", ids);
    }

    private Task getTask(Long id) {
        log.debug("Request to update task with ID: {}", id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }
}
