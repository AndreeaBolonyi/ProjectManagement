package ro.ubb.pm.bll;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ro.ubb.pm.bll.tasks.TaskFactory;
import ro.ubb.pm.bll.users.UserFactory;

@Configuration
@Import({UserFactory.class, TaskFactory.class})
@ComponentScan(basePackages = {"ro.ubb.pm.bll"})
public class BLLAutoConfiguration {
}
