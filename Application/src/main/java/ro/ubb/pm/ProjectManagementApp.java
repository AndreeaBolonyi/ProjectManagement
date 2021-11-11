package ro.ubb.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ro.ubb.pm.dal")
@EntityScan(basePackages = "ro.ubb.pm.model")
public class ProjectManagementApp {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementApp.class, args);
    }
}