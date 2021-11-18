package ro.ubb.pm.bll;

import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Component;
import ro.ubb.pm.dal.SprintsRepository;


@Component
public class SprintBLL {
    private SprintsRepository sprintsRepository;

    @Autowired
    public void setSprintsRepository(SprintsRepository sprintsRepository){
        this.sprintsRepository= sprintsRepository;
    }
}
