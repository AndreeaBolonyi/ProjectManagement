package ro.ubb.pm.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.pm.model.Epic;

@Repository
public interface EpicsRepository extends JpaRepository<Epic, Integer> {
}
