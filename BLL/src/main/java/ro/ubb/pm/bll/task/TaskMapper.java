package ro.ubb.pm.bll.task;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.ubb.pm.model.Task;
import ro.ubb.pm.model.dtos.TaskDTO;

@Mapper
@DecoratedWith(TaskMapperDecorator.class)
public interface TaskMapper {

    @Mapping(source = "assignedTo.id", target = "assignedToId")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "userStory.id", target = "userStoryId")
    TaskDTO taskToTaskDTO(Task task);

    @Mapping(target = "assignedTo", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "userStory", ignore = true)
    Task taskDTOToTask(TaskDTO taskDTO);
}
