package ro.ubb.pm.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.pm.model.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
}
