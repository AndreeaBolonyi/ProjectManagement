package ro.ubb.pm.bll;

import ro.ubb.pm.bll.validator.Validator;
import ro.ubb.pm.bll.validator.ValidatorUser;
import ro.ubb.pm.dal.*;
import ro.ubb.pm.model.*;

import java.util.ArrayList;
import java.util.List;

public class UserBLL {

    private UsersRepository usersRepository;
    private ValidatorUser validatorUser;

    public UserBLL(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void login(User user) throws Exception {
        validatorUser.validate(user);
        User u = new User();
        for(User us : getAllUsers())
            if(user.getEmail().equals(us.getEmail()) && user.getPassword().equals(us.getPassword())){
                u = user;
                break;
            }

        if(u.getLastName() == null)
            throw new ProjectException("The credentials are incorrect!");
    }

    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }
}
