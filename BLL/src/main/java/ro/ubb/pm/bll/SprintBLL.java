package ro.ubb.pm.bll;

import org.springframework.stereotype.Component;
import ro.ubb.pm.dal.SprintsRepository;
import ro.ubb.pm.model.Sprint;

import java.util.ArrayList;
import java.util.List;
@Component
public class SprintBLL {
    private SprintsRepository sprintsRepository;

    public SprintBLL(SprintsRepository sprintsRepository) {
        this.sprintsRepository = sprintsRepository;
    }

    public List<Sprint> getAllSprints(){
        return sprintsRepository.findAll();
    }

    public List<Sprint> getAllSprintsForUser(int id){
        List<Sprint> sprints = new ArrayList<>();

        for(Sprint s: getAllSprints())
            if(s.getId() == id)
                sprints.add(s);

        return sprints;
    }
}
