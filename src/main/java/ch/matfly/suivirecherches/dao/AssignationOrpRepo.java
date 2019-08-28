package ch.matfly.suivirecherches.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.matfly.suivirecherches.model.Adresse;
import ch.matfly.suivirecherches.model.AssignationOrp;

public interface AssignationOrpRepo  extends JpaRepository<AssignationOrp, Integer>{

}
