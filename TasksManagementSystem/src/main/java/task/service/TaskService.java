package task.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import task.service.dto.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskDTO save(TaskDTO task);

    TaskDTO update(Long id, TaskDTO task);

    TaskDTO findById(long id);

    List<TaskDTO> findTasksByIsSynced(boolean isSynced);

    List<TaskDTO> findAll();

    boolean delete(long id);

    void deleteAll(List<Long> ids);

}
