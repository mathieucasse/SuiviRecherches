package ch.matfly.suivirecherches.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.matfly.suivirecherches.model.Personne;

public interface PersonneRepo  extends JpaRepository<Personne, Long>{

}
