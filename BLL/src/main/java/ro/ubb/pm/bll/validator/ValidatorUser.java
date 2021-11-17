package ro.ubb.pm.bll.validator;

import ro.ubb.pm.model.User;

import java.util.ArrayList;
import java.util.List;

public class ValidatorUser implements Validator<User> {
    @Override
    public void validate(User u) throws ValidationException {
        List<String> msj = new ArrayList<>();
        StringBuilder message= new StringBuilder();

        if(u.getEmail().length() < 11)
            msj.add("Adresa de e-mail este invalida!");

        if(u.getPassword().equals(""))
            msj.add("Parola este invalida!");

        for(String s : msj)
            message.append(s);

        if(message.length() > 0)
            throw new ValidationException(message.toString());
    }
}