package ch.matfly.suivirecherches.dao;

import ch.matfly.suivirecherches.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
