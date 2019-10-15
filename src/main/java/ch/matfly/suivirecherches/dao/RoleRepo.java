package ch.matfly.suivirecherches.dao;

import ch.matfly.suivirecherches.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, String> {
}
