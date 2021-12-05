package ro.ubb.pm.bll.tasks;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.ubb.pm.model.Task;
import ro.ubb.pm.model.dtos.TaskDTO;

@Mapper
@DecoratedWith(TaskMapperDecorator.class)
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "assignedTo", target = "assignedToDTO")
    @Mapping(source = "createdBy", target = "createdByDTO")
    @Mapping(source = "userStory", target = "userStoryDTO")
    TaskDTO taskToTaskDTO(Task task);

    @Mapping(target = "assignedTo", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "userStory", ignore = true)
    Task taskDTOToTask(TaskDTO taskDTO);
}
