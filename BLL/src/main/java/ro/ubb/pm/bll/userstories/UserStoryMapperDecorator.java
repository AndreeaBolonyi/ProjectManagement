package ro.ubb.pm.bll.userstories;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.pm.bll.sprints.SprintMapper;
import ro.ubb.pm.bll.users.UserMapper;
import ro.ubb.pm.dal.RolesRepository;
import ro.ubb.pm.model.Role;
import ro.ubb.pm.model.User;
import ro.ubb.pm.model.UserStory;
import ro.ubb.pm.model.dtos.SprintDTO;
import ro.ubb.pm.model.dtos.UserDTO;
import ro.ubb.pm.model.dtos.UserStoryDTO;

public abstract class UserStoryMapperDecorator implements UserStoryMapper {

    private final UserStoryMapper userStoryMapper;
    private UserMapper userMapper;
    private SprintMapper sprintMapper;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setSprintMapper(SprintMapper sprintMapper) {
        this.sprintMapper = sprintMapper;
    }

    public UserStoryMapperDecorator(UserStoryMapper userStoryMapper)
    {
        this.userStoryMapper = userStoryMapper;
    }


    @Override
    public UserStoryDTO userStoryToUserStoryDTO(UserStory userStory) {

        UserStoryDTO userStoryDTO = userStoryMapper.userStoryToUserStoryDTO(userStory);
        UserDTO assignedToUserDTO = userMapper.userToUserDTO(userStory.getAssignedTo());
        userStoryDTO.setAssignedTo(assignedToUserDTO);

        UserDTO createdByUserDTO = userMapper.userToUserDTO(userStory.getCreatedBy());
        userStoryDTO.setCreatedBy(createdByUserDTO);

        SprintDTO sprintDTO = sprintMapper.sprintToSprintDTO(userStory.getSprint());
        userStoryDTO.setSprintDTO(sprintDTO);

        return userStoryDTO;
    }
}
