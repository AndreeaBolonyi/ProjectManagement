package ro.ubb.pm.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.pm.bll.tasks.TaskBLL;
import ro.ubb.pm.model.dtos.TaskDTO;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(TaskController.BASE_URL)
public class TaskController {

    protected static final String BASE_URL = "project_management/tasks";

    private final TaskBLL taskBLL;

    public TaskController(TaskBLL taskBLL) {
        this.taskBLL = taskBLL;
    }

    /**
     * Retrieves all the tasks associated to an user story.
     * @param userStoryId - int
     *                    - id of the sprint
     * @return ResponseEntity<List<TaskDTO>> - all
     */
    @RequestMapping(value = "/get-all-by-user-story/{userStoryId}", method = RequestMethod.GET)
    public ResponseEntity<List<TaskDTO>> getAllTasksByUserStoryId(@PathVariable int userStoryId) {
        return new ResponseEntity<>(taskBLL.getAllTasksForAUserStory(userStoryId), HttpStatus.OK);
    }

}
