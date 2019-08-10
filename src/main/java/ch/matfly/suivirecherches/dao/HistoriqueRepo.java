package ch.matfly.suivirecherches.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.matfly.suivirecherches.model.Historique;

public interface HistoriqueRepo extends JpaRepository<Historique, Long>{

	List<Historique> findByRechercheId(Long valueOf);

}
