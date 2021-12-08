package ro.ubb.pm.bll.userstories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.pm.dal.UserStoriesRepository;
import ro.ubb.pm.model.UserStory;
import ro.ubb.pm.model.dtos.UserStoryDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserStoryBLL {

    private UserStoriesRepository userStoriesRepository;
    private UserStoryMapper userStoryMapper;

    @Autowired
    public void setUserStoriesRepository(UserStoriesRepository userStoriesRepository) {
        this.userStoriesRepository = userStoriesRepository;
    }

    @Autowired
    public void setUserStoryMapper(UserStoryMapper userStoryMapper) {
        this.userStoryMapper = userStoryMapper;
    }

    public List<UserStoryDTO> getAllUserStoriesBySprintId(int id){
        return userStoriesRepository.findAllBySprintId(id)
                .stream()
                .map(userStoryMapper::userStoryToUserStoryDTO)
                .collect(Collectors.toList());
    }

    public UserStoryDTO addUserStory(UserStoryDTO userStoryDTO) {
        return null;
    }

    public UserStoryDTO updateUserStory(UserStoryDTO userStoryDTO) {
        return null;
    }

    public void deleteUserStory(int userStoryId) {
        return ;
    }

    public UserStory findUserStoryById(int userStoryId) {
        return userStoriesRepository.getById(userStoryId);
    }

}
