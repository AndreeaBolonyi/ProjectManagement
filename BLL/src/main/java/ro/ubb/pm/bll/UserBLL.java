package ro.ubb.pm.bll;

import org.springframework.stereotype.Component;
import ro.ubb.pm.bll.validator.ValidationException;
import ro.ubb.pm.bll.validator.ValidatorUser;
import ro.ubb.pm.dal.*;
import ro.ubb.pm.model.*;

import java.util.List;

@Component
public class UserBLL {

    private UsersRepository usersRepository;
    private ValidatorUser validatorUser;

    public UserBLL(UsersRepository usersRepository, ValidatorUser validatorUser) {
        this.usersRepository = usersRepository;
        this.validatorUser = validatorUser;
    }

    public void login(User user) throws ProjectException, ValidationException {
        validatorUser.validate(user);
        User u = usersRepository.findByEmail(user.getEmail());
        if(u == null)
            throw new ProjectException("The credentials are incorrect!");
    }

    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }
}
