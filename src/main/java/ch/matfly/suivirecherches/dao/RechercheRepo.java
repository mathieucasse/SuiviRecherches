package ch.matfly.suivirecherches.dao;

import ch.matfly.suivirecherches.model.Recherche;
import ch.matfly.suivirecherches.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RechercheRepo extends JpaRepository<Recherche, Long>{

    List<Recherche> findAllByOwner(User u);
}
