package ro.ubb.pm.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.pm.model.Epic;

import java.util.List;

@Repository
public interface EpicsRepository extends JpaRepository<Epic, Integer> {

    @Query("FROM Epic epic WHERE epic.project.id =: projectId")
    List<Epic> findAllByProjectId(int projectId);
}
