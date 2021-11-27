package ro.ubb.pm.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.pm.bll.userstories.UserStoryBLL;
import ro.ubb.pm.model.UserStory;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(UserStoryController.BASE_URL)
public class UserStoryController {

    protected static final String BASE_URL = "project_management/user-stories";

    private final UserStoryBLL userStoryBLL;

    public UserStoryController(UserStoryBLL userStoryBLL) {
        this.userStoryBLL = userStoryBLL;
    }

    /**
     * get all user stories that are associated with the sprint id
     * @param sprintId - int
     * @return ResponseEntity<List<UserStory>> if sprintId is a valid number, else ResponseEntity<String> with the error message
     */
    @RequestMapping(value = "/get-all-by-sprint/{sprintId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUserStoriesBySprint(@PathVariable String sprintId) {
        List<UserStory> userStories;
        try {
            userStories = userStoryBLL.getAllUserStoriesBySprintId(Integer.parseInt(sprintId));
        }
        catch(NumberFormatException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userStories, HttpStatus.OK);
    }
}
