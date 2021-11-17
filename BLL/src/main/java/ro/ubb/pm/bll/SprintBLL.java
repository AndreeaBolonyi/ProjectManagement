package ro.ubb.pm.bll;

import ro.ubb.pm.dal.SprintsRepository;
import ro.ubb.pm.model.Sprint;
import ro.ubb.pm.model.Task;
import ro.ubb.pm.model.UserStory;

import java.util.ArrayList;
import java.util.List;

public class SprintBLL {

    private SprintsRepository sprintsRepository;
    private UserStoryBLL userStoryBLL;

    public SprintBLL(SprintsRepository sprintsRepository) {
        this.sprintsRepository = sprintsRepository;
    }

    public List<Sprint> getAllSprints(){
        return sprintsRepository.findAll();
    }

    public List<Task> getAllTasksForASprint(Sprint sprint){
        List<Task> taskList = new ArrayList<>();
        List<UserStory> userSList = new ArrayList<>();

        for(Sprint s : getAllSprints())
            if(s.getId() == sprint.getId()){
                userSList = userStoryBLL.getAllUserStoriesSprint(s.getId());
            }

        for(UserStory us : userSList)
            taskList.addAll(us.getTasks());

        return taskList;
    }
}
