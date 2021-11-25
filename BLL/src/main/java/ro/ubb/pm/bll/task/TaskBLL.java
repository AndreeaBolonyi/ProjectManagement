package ro.ubb.pm.bll.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.pm.dal.TasksRepository;
import ro.ubb.pm.model.dtos.TaskDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskBLL {

    private TasksRepository tasksRepository;
    private TaskMapper taskMapper;

    @Autowired
    public void setTasksRepository(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Autowired
    public void setTaskMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public List<TaskDTO> getAllTasksForAUserStory(int userStoryId){
        return tasksRepository.findAllByUserStoryId(userStoryId)
                .stream()
                .map(taskMapper::taskToTaskDTO)
                .collect(Collectors.toList());
    }
}
