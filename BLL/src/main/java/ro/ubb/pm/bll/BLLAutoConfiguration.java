package ro.ubb.pm.bll;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ro.ubb.pm.bll.task.TaskFactory;
import ro.ubb.pm.bll.user.UserFactory;

@Configuration
@Import({UserFactory.class, TaskFactory.class})
@ComponentScan(basePackages = {"ro.ubb.pm.bll"})
public class BLLAutoConfiguration {
}
