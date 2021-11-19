package ro.ubb.pm.bll;

import org.springframework.stereotype.Component;

@Component
public class ProjectException extends Exception{
    public ProjectException() {
    }

    public ProjectException(String message) {
        super(message);
    }

    public ProjectException(String message, Throwable cause) {
        super(message, cause);
    }
}