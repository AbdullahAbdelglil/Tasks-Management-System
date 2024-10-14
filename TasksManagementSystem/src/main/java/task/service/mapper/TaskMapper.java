package task.service.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import task.entity.Task;
import task.service.dto.TaskDTO;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO toDTO(Task task);

    Task toEntity(TaskDTO taskDTO);
}
