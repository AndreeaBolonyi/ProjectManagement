package ro.ubb.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.pm.bll.SprintBLL;
import ro.ubb.pm.bll.TaskBLL;
import ro.ubb.pm.bll.UserBLL;
import ro.ubb.pm.bll.UserStoryBLL;
import ro.ubb.pm.model.Sprint;
import ro.ubb.pm.model.Task;
import ro.ubb.pm.model.User;
import ro.ubb.pm.model.UserStory;
import ro.ubb.pm.model.dtos.UserDTO;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/project_management")
public class Controller {
    @Autowired
    private SprintBLL sprintBLL;
    @Autowired
    private TaskBLL taskBLL;
    @Autowired
    private UserBLL userBLL;
    @Autowired
    private UserStoryBLL userStoryBLL;

    /**
     * Retrieves all the sprints.
     * @return
     */
    @RequestMapping(value = "/sprint", method = RequestMethod.GET)
    public Sprint[] getAllSprints() {
        List<Sprint> sprints = sprintBLL.getAllSprints();
        return sprints.toArray(new Sprint[sprints.size()]);
    }

    /**
     * Retrieves all the tasks associated to a sprint.
     * @param sprintID - id of the sprint
     * @return
     */
    @RequestMapping(value = "/task/{sprintID}", method = RequestMethod.GET)
    public Sprint[] getTasksBySprint(@PathVariable String sprintID) {
        List<Task> tasksBySprint = taskBLL.getAllTasksForASprint(Integer.getInteger(sprintID));
        return tasksBySprint.toArray(new Sprint[tasksBySprint.size()]);
    }

    /**
     * Executes the login operation for an user.
     * @param
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        User userFound;
        try {
            userFound = userBLL.login(userDTO.getEmail(), userDTO.getPassword());
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userFound, HttpStatus.OK);
    }

    /**
     * Retrieves all the user stories.
      * @return
     */
    @RequestMapping(value = "/userstory", method = RequestMethod.GET)
    public UserStory[] getAllUserStories() {
        List<UserStory> userStories = userStoryBLL.getAllUserStories();
        return userStories.toArray(new UserStory[userStories.size()]);
    }
}
