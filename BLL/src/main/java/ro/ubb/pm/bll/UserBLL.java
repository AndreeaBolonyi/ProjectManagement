package ro.ubb.pm.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.pm.bll.validator.ValidationException;
import ro.ubb.pm.bll.validator.ValidatorUser;
import ro.ubb.pm.dal.*;
import ro.ubb.pm.model.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserBLL {

    private UsersRepository usersRepository;
    private ValidatorUser validatorUser;

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    public void setValidatorUser(ValidatorUser validatorUser) {
        this.validatorUser = validatorUser;
    }

    public void login(User user) throws ServerException, ValidationException {
        validatorUser.validate(user);
        User u = usersRepository.findByEmail(user.getEmail());
        if(u == null)
            throw new ServerException("The email you've entered is incorrect!");
        if(!u.getPassword().equals(user.getPassword()))
            throw new ServerException("The password you've entered is incorrect!");
    }
}
