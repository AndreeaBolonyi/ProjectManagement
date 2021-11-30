package ro.ubb.pm.bll.sprints;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.pm.bll.user.UserMapper;
import ro.ubb.pm.bll.user.UserMapperImpl;

@Configuration
public class SprintFactory {

    @Bean
    public SprintMapper sprintMapper() {
        return new SprintMapperImpl();
    }


}
