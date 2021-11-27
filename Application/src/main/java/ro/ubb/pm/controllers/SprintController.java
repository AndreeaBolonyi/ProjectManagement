package ro.ubb.pm.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.pm.bll.sprints.SprintBLL;

@RestController
@CrossOrigin
@RequestMapping(SprintController.BASE_URL)
public class SprintController {

    protected static final String BASE_URL = "project_management";

    private final SprintBLL sprintBLL;

    public SprintController(SprintBLL sprintBLL) {
        this.sprintBLL = sprintBLL;
    }

}
