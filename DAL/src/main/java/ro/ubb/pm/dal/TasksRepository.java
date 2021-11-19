package ro.ubb.pm.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.pm.model.Task;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Task, Integer> {

    @Query("FROM Task task WHERE task.userStory.id = :userStoryId")
    List<Task> findAllByUserStoryId(int userStoryId);
}
