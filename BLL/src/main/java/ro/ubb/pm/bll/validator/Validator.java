package ro.ubb.pm.bll.validator;

import org.springframework.stereotype.Component;

@Component
public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
