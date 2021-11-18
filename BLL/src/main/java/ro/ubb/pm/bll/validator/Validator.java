package ro.ubb.pm.bll.validator;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
