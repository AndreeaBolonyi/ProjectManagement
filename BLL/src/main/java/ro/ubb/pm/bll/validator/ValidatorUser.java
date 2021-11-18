package ro.ubb.pm.bll.validator;

import org.springframework.stereotype.Component;
import ro.ubb.pm.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidatorUser implements Validator<User> {
    @Override
    public void validate(User u) throws ValidationException {
        List<String> msj = new ArrayList<>();
        StringBuilder message= new StringBuilder();

        if(u == null)
            throw new ValidationException("User-ul nu poate fi null!");

        if(u.getEmail().length() < 11 || u.getEmail().isEmpty())
            msj.add("Adresa de e-mail este invalida!");

        if(u.getPassword().isEmpty())
            msj.add("Parola este invalida!");

        for(String s : msj)
            message.append(s);

        if(message.length() > 0)
            throw new ValidationException(message.toString());
    }
}