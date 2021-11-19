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

import java.util.List;

@RestController
@RequestMapping("/urest")
public class Controller {
    private static final String template = "Hello, %s!";
    @Autowired
    private SprintBLL sprintBLL;
    @Autowired
    private TaskBLL taskBLL;
    @Autowired
    private UserBLL userBLL;
    @Autowired
    private UserStoryBLL userStoryBLL;

    /**
     *
     * @param name
     * @return
     */
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format(template, name);
    }

    /**
     * Retrieves all the sprints.
     * @return
     */
    @RequestMapping(value = "/sprint", method = RequestMethod.GET)
    public Sprint[] getAllSprint() {
        List<Sprint> sprints = sprintBLL.getAllSprints();
        return sprints.toArray(new Sprint[sprints.size()]);
    }

    /**
     * Retrieves all the tasks associated to a sprint.
     * @param sprintID - id of the sprint
     * @return
     */
    @RequestMapping(value = "/task/{sprintID}", method = RequestMethod.GET)
    public Sprint[] getTaskBySprint(@PathVariable String sprintID) {
        List<Task> tasksBySprint = taskBLL.getAllTasksForASprint(Integer.getInteger(sprintID));
        return tasksBySprint.toArray(new Sprint[tasksBySprint.size()]);
    }

    /**
     * Executes the login operation for an user.
     * @param email - the email associated to the user account
     * @return
     */
    /*
    TO DO: Implement secure connection and delete this mock logic.
     */
    @RequestMapping(value = "/login/{email}")
    public ResponseEntity<HttpStatus> loginUser(@PathVariable String email) {
        try {
            userBLL.login(email, email.substring(0, email.indexOf("@")));
        } catch (Exception ex) {
            return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Retrieves all the user stories.
      * @return
     */
    @RequestMapping(value = "/userstory", method = RequestMethod.GET)
    public UserStory[] getAllUserStory() {
        List<UserStory> userStories = userStoryBLL.getAllUserStories();
        return userStories.toArray(new UserStory[userStories.size()]);
    }
}
