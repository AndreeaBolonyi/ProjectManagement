package ro.ubb.pm.bll.sprints;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.pm.bll.user.UserMapper;
import ro.ubb.pm.dal.RolesRepository;
import ro.ubb.pm.dal.SprintsRepository;
import ro.ubb.pm.model.Role;
import ro.ubb.pm.model.Sprint;
import ro.ubb.pm.model.User;
import ro.ubb.pm.model.dtos.SprintDTO;
import ro.ubb.pm.model.dtos.UserDTO;

public abstract class SprintMapperDecorator implements SprintMapper {

    private final SprintMapper sprintMapper;

    @Autowired
    private SprintsRepository sprintsRepository;

    public SprintMapperDecorator(SprintMapper sprintMapper) {
        this.sprintMapper = sprintMapper;
    }

    @Override
    public Sprint sprintDTOToSprint(SprintDTO sprintDTO) {
        return sprintsRepository.findById(sprintDTO.getId()).orElse(null);
    }
}
