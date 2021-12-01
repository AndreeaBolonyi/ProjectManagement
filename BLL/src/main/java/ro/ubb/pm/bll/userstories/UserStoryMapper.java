package ro.ubb.pm.bll.userstories;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.ubb.pm.bll.users.UserMapperDecorator;
import ro.ubb.pm.model.User;
import ro.ubb.pm.model.UserStory;
import ro.ubb.pm.model.dtos.UserDTO;
import ro.ubb.pm.model.dtos.UserStoryDTO;

@Mapper
@DecoratedWith(UserStoryMapperDecorator.class)
public interface UserStoryMapper {

    UserStoryMapper INSTANCE = Mappers.getMapper(ro.ubb.pm.bll.userstories.UserStoryMapper.class);

    @Mapping(source = "sprint", target = "sprintDTO")
    UserStoryDTO userStoryToUserStoryDTO(UserStory userStory);

    @Mapping(target = "epic", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    UserStory userStoryDTOToUserStory(UserStoryDTO userStoryDTO);
}