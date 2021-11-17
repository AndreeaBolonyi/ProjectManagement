package ro.ubb.pm.bll;

import ro.ubb.pm.dal.UserStoriesRepository;
import ro.ubb.pm.model.UserStory;

import java.util.ArrayList;
import java.util.List;

public class UserStoryBLL {

    private UserStoriesRepository userStoriesRepository;

    public UserStoryBLL(UserStoriesRepository userStoriesRepository) {
        this.userStoriesRepository = userStoriesRepository;
    }

    public List<UserStory> getAllUserStories(){
        return userStoriesRepository.findAll();
    }

    public List<UserStory> getAllUserStoriesSprint(int id){
        List<UserStory> uList = new ArrayList<>();

        for(UserStory us : getAllUserStories())
            if(us.getSprint().getId() == id)
                uList.add(us);

        return uList;
    }
}
