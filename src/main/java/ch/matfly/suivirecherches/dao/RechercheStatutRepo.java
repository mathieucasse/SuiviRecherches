package ch.matfly.suivirecherches.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.matfly.suivirecherches.model.Adresse;
import ch.matfly.suivirecherches.model.RechercheStatut;

public interface RechercheStatutRepo  extends JpaRepository<RechercheStatut, Integer>{

}
