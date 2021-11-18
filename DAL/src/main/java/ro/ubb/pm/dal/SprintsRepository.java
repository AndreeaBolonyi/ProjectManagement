package ro.ubb.pm.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.pm.model.Sprint;

import java.util.List;

@Repository
public interface SprintsRepository extends JpaRepository<Sprint, Integer> {
}
