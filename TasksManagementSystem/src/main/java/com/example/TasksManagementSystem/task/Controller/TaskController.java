package com.example.TasksManagementSystem.task.Controller;

import com.example.TasksManagementSystem.task.service.TaskService;
import com.example.TasksManagementSystem.task.service.dto.DeleteTasksRequestDTO;
import com.example.TasksManagementSystem.task.service.dto.TaskDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO) throws URISyntaxException {
        log.debug("Request to add a new task: {}", taskDTO);
        TaskDTO savedTask = taskService.save(taskDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTask.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedTask);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("taskId") Long taskId) {
        log.debug("REST request to get task by id: {}", taskId);
        TaskDTO task = taskService.findById(taskId);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        log.debug("REST request to get all tasks");
        List<TaskDTO> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/synced")
    public ResponseEntity<List<TaskDTO>> getAllSyncedTasks() {
        log.debug("REST request to get all tasks where isSynced is true");
        List<TaskDTO> tasks = taskService.findTasksByIsSynced(true);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskDTO taskDTO) {
        log.debug("REST request to update task by id {} with {}", taskId, taskDTO);
        TaskDTO updatedTask = taskService.update(taskId, taskDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId) {
        log.debug("REST request to delete task by id {}", taskId);
        taskService.delete(taskId);
        log.info("Task with id {} successfully deleted", taskId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTaskByIds(@RequestBody DeleteTasksRequestDTO deleteTasksRequestDTO) {
        List<Long> ids = deleteTasksRequestDTO.getIds();
        log.debug("REST request to delete tasks with ids: {}", ids);
        taskService.deleteAll(ids);
        log.info("Tasks with ids {} successfully deleted", ids);
        return ResponseEntity.noContent().build();
    }
}
