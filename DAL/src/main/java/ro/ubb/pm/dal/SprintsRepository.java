package ro.ubb.pm.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.pm.model.Sprint;

@Repository
public interface SprintsRepository extends JpaRepository<Sprint, Integer> {
}
