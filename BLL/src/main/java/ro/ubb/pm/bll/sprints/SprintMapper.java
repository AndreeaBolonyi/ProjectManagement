package ro.ubb.pm.bll.sprints;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.ubb.pm.bll.user.UserMapperDecorator;
import ro.ubb.pm.model.Sprint;
import ro.ubb.pm.model.User;
import ro.ubb.pm.model.dtos.EpicDTO;
import ro.ubb.pm.model.dtos.SprintDTO;
import ro.ubb.pm.model.dtos.UserDTO;

@Mapper
@DecoratedWith(SprintMapperDecorator.class)
public interface SprintMapper {

    SprintMapper INSTANCE = Mappers.getMapper(ro.ubb.pm.bll.sprints.SprintMapper.class);

    @Mapping(source = "epic", target = "epicDTO")
    SprintDTO sprintToSprintDTO(Sprint sprint);

    @Mapping(target = "epic", ignore = true)
    @Mapping(target = "userStories", ignore = true)
    Sprint sprintDTOToSprint(SprintDTO sprintDTO);
}
