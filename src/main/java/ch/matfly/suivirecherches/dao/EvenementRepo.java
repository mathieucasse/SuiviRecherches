package ch.matfly.suivirecherches.dao;

import ch.matfly.suivirecherches.model.Evenement;
import ch.matfly.suivirecherches.model.Recherche;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvenementRepo extends JpaRepository<Evenement, Long> {

    List<Evenement> findAllByRecherche(Recherche recherche);
}