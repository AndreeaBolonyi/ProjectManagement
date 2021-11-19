package ro.ubb.pm.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.pm.dal.TasksRepository;
import ro.ubb.pm.model.Sprint;
import ro.ubb.pm.model.Task;
import ro.ubb.pm.model.UserStory;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskBLL {
    private TasksRepository tasksRepository;
    private UserStoryBLL userStoryBLL;
    private SprintBLL sprintBLL;

    @Autowired
    public void setTasksRepository(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Autowired
    public void setUserStoryBLL(UserStoryBLL userStoryBLL) {
        this.userStoryBLL = userStoryBLL;
    }

    @Autowired
    public void setSprintBLL(SprintBLL sprintBLL) {
        this.sprintBLL = sprintBLL;
    }

    public List<Task> getAllTasksForASprint(int sprintId){
        Sprint currentSprint = sprintBLL.getSprintById(sprintId);
        List<UserStory> userStories = currentSprint.getUserStories();
        List<Task> tasksForCurrentSprint = new ArrayList<>();

        for(UserStory userStory : userStories) {
            List<Task> tasks = tasksRepository.findAllByUserStoryId(userStory.getId());
            tasksForCurrentSprint.addAll(tasks);
        }

        return tasksForCurrentSprint;
    }
}
