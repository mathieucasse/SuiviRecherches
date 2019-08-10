package ch.matfly.suivirecherches.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.matfly.suivirecherches.model.Recherche;

public interface RechercheRepo extends JpaRepository<Recherche, Long>{

}
