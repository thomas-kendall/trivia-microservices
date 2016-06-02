package trivia.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trivia.usermanagement.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
