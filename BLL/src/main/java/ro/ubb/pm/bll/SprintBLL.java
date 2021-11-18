package ro.ubb.pm.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;
import ro.ubb.pm.dal.SprintsRepository;
import ro.ubb.pm.model.Sprint;

import java.util.ArrayList;
import java.util.List;

@Component
@EntityScan(basePackages = "ro.ubb.pm.dal")
public class SprintBLL {
    private SprintsRepository sprintsRepository;

  /*  public SprintBLL(SprintsRepository sprintsRepository) {
=======
public class SprintBLL {
    private SprintsRepository sprintsRepository;

    @Autowired
    public void setTasksRepository(SprintsRepository sprintsRepository) {
>>>>>>> fe296bedc5aa441bc8c50560d91518399b11a694
        this.sprintsRepository = sprintsRepository;
    }
*/

    @Autowired
    public void setSprintsRepository(SprintsRepository sprintsRepository){
        this.sprintsRepository= sprintsRepository;
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
