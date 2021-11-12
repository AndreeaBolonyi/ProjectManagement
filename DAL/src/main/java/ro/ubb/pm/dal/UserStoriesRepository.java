package ro.ubb.pm.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.pm.model.UserStory;

@Repository
public interface UserStoriesRepository extends JpaRepository<UserStory, Integer> {
}
