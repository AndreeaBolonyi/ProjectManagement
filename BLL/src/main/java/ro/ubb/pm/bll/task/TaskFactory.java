package ro.ubb.pm.bll.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskFactory {

    @Bean
    public TaskMapper taskMapper() {
        return new TaskMapperImpl();
    }
}
