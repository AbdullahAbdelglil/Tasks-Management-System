package com.example.TasksManagementSystem.task.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.TasksManagementSystem.task.service.TaskService;
import com.example.TasksManagementSystem.task.service.dto.TaskDTO;

@Controller
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("")
    public TaskDTO addTask(@RequestBody TaskDTO taskDTO) {
        return taskService.save(taskDTO);
    }

    @GetMapping("/{taskId}")
    public TaskDTO getTaskById(@PathVariable("taskId") Long taskId) {
        log.debug("Rest request to get task by id: {}", taskId);
        return taskService.findById(taskId);
    }

}
