package ro.ubb.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
<<<<<<< HEAD
=======
@EntityScan(basePackages = "ro.ubb.pm")
>>>>>>> main
public class ProjectManagementApp {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementApp.class, args);
    }
}