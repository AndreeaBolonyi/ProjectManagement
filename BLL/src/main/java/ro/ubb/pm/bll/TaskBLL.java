package ro.ubb.pm.bll;

import ro.ubb.pm.dal.SprintsRepository;
import ro.ubb.pm.dal.TasksRepository;
import ro.ubb.pm.model.Sprint;
import ro.ubb.pm.model.Task;
import ro.ubb.pm.model.UserStory;

import java.util.ArrayList;
import java.util.List;

public class TaskBLL {

    private TasksRepository tasksRepository;
    private UserStoryBLL userStoryBLL;
    private SprintBLL sprintBLL;

    public TaskBLL(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public void setBLL(UserStoryBLL userStoryBLL, SprintBLL sprintBLL){
        this.userStoryBLL = userStoryBLL;
        this.sprintBLL = sprintBLL;
    }

    public List<Task> getAllTasksForASprint(int id){
        List<Task> taskList = new ArrayList<>();
        List<UserStory> userSList = new ArrayList<>();

        for(Sprint s : sprintBLL.getAllSprintsForUser(id))
            if(s.getId() == id){
                userSList = userStoryBLL.getAllUserStoriesSprint(s.getId());
            }

        for(UserStory us : userSList)
            taskList.addAll(us.getTasks());

        return taskList;
    }
}
