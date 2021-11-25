package ro.ubb.pm.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.pm.bll.validator.ValidationException;
import ro.ubb.pm.bll.validator.ValidatorUser;
import ro.ubb.pm.dal.*;
import ro.ubb.pm.model.*;

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

    public User login(String email, String password) throws ServerException, ValidationException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        validatorUser.validate(user);

        User userFound = usersRepository.findByEmail(user.getEmail());
        if(userFound == null)
            throw new ServerException("The email you've entered is incorrect!");
        if(!userFound.getPassword().equals(user.getPassword()))
            throw new ServerException("The password you've entered is incorrect!");

        return userFound;
    }
}
