package ch.matfly.suivirecherches.dao;

import ch.matfly.suivirecherches.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepo extends JpaRepository<Evenement, Long> {
}