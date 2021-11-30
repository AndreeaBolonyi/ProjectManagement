package ro.ubb.pm.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.ubb.pm.bll.users.UserBLL;
import ro.ubb.pm.bll.exceptions.InvalidCredentialsException;
import ro.ubb.pm.model.dtos.UserDTO;

@RestController
@CrossOrigin
@RequestMapping(UserController.BASE_URL)
public class UserController {

    protected static final String BASE_URL = "project_management";

    private final UserBLL userBLL;

    public UserController(UserBLL userBLL) {
        this.userBLL = userBLL;
    }

    /**
     * Executes the login operation for an user.
     * @param userDTO - UserDTO
     * @return ResponseEntity<UserDTO> - if the entered credentials are correct
     * @throws InvalidCredentialsException - if the entered credentials are incorrect
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> login(@Validated @RequestBody UserDTO userDTO) throws InvalidCredentialsException {
        UserDTO userFound;
        try {
            userFound = userBLL.login(userDTO);
        } catch (InvalidCredentialsException ex) {
            throw new InvalidCredentialsException(ex.getMessage());
        }
        return new ResponseEntity<>(userFound, HttpStatus.OK);
    }
}
