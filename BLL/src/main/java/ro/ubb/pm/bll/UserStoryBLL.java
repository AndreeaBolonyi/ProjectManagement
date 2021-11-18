package ro.ubb.pm.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.pm.dal.UserStoriesRepository;
import ro.ubb.pm.model.UserStory;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserStoryBLL {

    private UserStoriesRepository userStoriesRepository;

    @Autowired
    public void setUserStoriesRepository(UserStoriesRepository userStoriesRepository) {
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
