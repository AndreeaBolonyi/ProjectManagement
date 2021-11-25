package ro.ubb.pm.controllers;

import org.springframework.web.bind.annotation.*;
import ro.ubb.pm.bll.userstories.UserStoryBLL;

@RestController
@CrossOrigin
@RequestMapping(UserStoryController.BASE_URL)
public class UserStoryController {

    protected static final String BASE_URL = "project-management";

    private final UserStoryBLL userStoryBLL;

    public UserStoryController(UserStoryBLL userStoryBLL) {
        this.userStoryBLL = userStoryBLL;
    }

}
