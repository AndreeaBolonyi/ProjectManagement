package ro.ubb.pm.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.pm.model.Task;

@Repository
public interface TasksRepository extends JpaRepository<Task, Integer> {
}
