package ro.ubb.pm.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.pm.bll.userstories.UserStoryBLL;
import ro.ubb.pm.model.dtos.UserStoryDTO;

import java.util.List;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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
     * @return ResponseEntity<List<UserStoryDTO>>
     */
    @RequestMapping(value = "/get-all-by-sprint/{sprintId}", method = RequestMethod.GET)
    public ResponseEntity<List<UserStoryDTO>> getAllUserStoriesBySprint(@PathVariable int sprintId) {
        return new ResponseEntity<>(userStoryBLL.getAllUserStoriesBySprintId(sprintId), HttpStatus.OK);
    }

}
