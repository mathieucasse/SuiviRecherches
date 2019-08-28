package ch.matfly.suivirecherches.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.matfly.suivirecherches.model.TauxActivite;

public interface TauxActiviteRepo  extends JpaRepository<TauxActivite, Integer>{

}
