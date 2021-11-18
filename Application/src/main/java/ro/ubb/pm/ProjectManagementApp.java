package ro.ubb.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "ro.ubb.pm")
public class ProjectManagementApp {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementApp.class, args);
    }
}