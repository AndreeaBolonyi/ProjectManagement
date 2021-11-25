package ro.ubb.pm.bll.userstories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.pm.dal.UserStoriesRepository;
import ro.ubb.pm.model.UserStory;
import java.util.List;

@Component
public class UserStoryBLL {

    private UserStoriesRepository userStoriesRepository;

    @Autowired
    public void setUserStoriesRepository(UserStoriesRepository userStoriesRepository) {
        this.userStoriesRepository = userStoriesRepository;
    }

    public List<UserStory> getAllUserStoriesBySprintId(int id){
        return userStoriesRepository.findAllBySprintId(id);
    }

    public List<UserStory> getAllUserStories() {
        return userStoriesRepository.findAll();
    }

    public UserStory findUserStoryById(int userStoryId) {
        return userStoriesRepository.getById(userStoryId);
    }
}