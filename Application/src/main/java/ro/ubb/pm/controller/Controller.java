package ro.ubb.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.pm.bll.SprintBLL;
import ro.ubb.pm.bll.UserBLL;
import ro.ubb.pm.bll.UserStoryBLL;
import ro.ubb.pm.model.Sprint;
import ro.ubb.pm.model.User;
import ro.ubb.pm.model.UserStory;
import ro.ubb.pm.model.dtos.UserDTO;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/project_management")
public class Controller {
    private SprintBLL sprintBLL;
    private UserBLL userBLL;
    private UserStoryBLL userStoryBLL;

    @Autowired
    public void setSprintBLL(SprintBLL sprintBLL) {
        this.sprintBLL = sprintBLL;
    }

    @Autowired
    public void setUserBLL(UserBLL userBLL) {
        this.userBLL = userBLL;
    }

    @Autowired
    public void setUserStoryBLL(UserStoryBLL userStoryBLL) {
        this.userStoryBLL = userStoryBLL;
    }

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
     * @return userStories - List<UserStory> if everything it's ok, otherwise HttpStatus.BAD_REQUEST
     */
    @RequestMapping(value = "/sprint/userstories/{sprintID}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserStoriesBySprint(@PathVariable int sprintID) {
        List<UserStory> userStories;
        try {
            userStories = userStoryBLL.getAllUserStoriesBySprintId(sprintID);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userStories, HttpStatus.OK);
    }

    /**
     * Executes the login operation for an user.
     * @param userDTO - UserDTO object which contains email and password
     * @return userFound - User if credentials are ok, otherwise HttpStatus.BAD_REQUEST
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
