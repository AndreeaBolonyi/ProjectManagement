package ro.ubb.pm.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.pm.model.UserStory;

import java.util.List;

@Repository
public interface UserStoriesRepository extends JpaRepository<UserStory, Integer> {

    @Query("FROM UserStory userStory WHERE userStory.sprint.id = :id")
    List<UserStory> findAllBySprintId(int id);
}
