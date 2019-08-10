package ch.matfly.suivirecherches.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.matfly.suivirecherches.model.Entreprise;

public interface EntrepriseRepo extends JpaRepository<Entreprise, Long>{

}
