package ch.matfly.suivirecherches.service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.matfly.suivirecherches.dao.HistoriqueRepo;
import ch.matfly.suivirecherches.dao.RechercheRepo;
import ch.matfly.suivirecherches.model.Historique;
import ch.matfly.suivirecherches.model.Recherche;
import ch.matfly.suivirecherches.model.dto.AngularRechercheDto;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RechercheService {

	@Autowired private RechercheRepo rechercheRepo;
	@Autowired private HistoriqueRepo historiqueRepo;
	
	
	public List<AngularRechercheDto> getRecherches() {
		return rechercheRepo.findAll().stream().map(AngularRechercheDto::new).collect(Collectors.toList());
	}
	
	
	public AngularRechercheDto updateRecherche(AngularRechercheDto aRecherche) {
		log.debug(">>>>>>>>>  RestController==== updateRecherche Called !!! ");
		Recherche recherche = rechercheRepo.findById(aRecherche.getId()).orElse(null);

		if(null != recherche) {
			log.debug(">>>>>>>>>  Service Recherche ==== updateRecherche ==  " + recherche);
			recherche.updateWith(aRecherche);
			recherche = rechercheRepo.save(recherche);
			return new AngularRechercheDto(recherche);
		} else {
			return null;
		}
	}
	
	public AngularRechercheDto addRecherche(AngularRechercheDto aRecherche) throws ParseException {
		log.debug(">>>>>>>>>  Service ==== addRecherche Called !!! ");
		Recherche recherche = aRecherche.buildRecherche();
		log.debug("========== addRecherche  : " + recherche);
		recherche = rechercheRepo.save(recherche);
		if(null != recherche.getId()) {
			log.debug("added Recherche : " + recherche.toString());
			return new AngularRechercheDto(recherche);
		}else {
			return null;
		}
	}
	
	public AngularRechercheDto delRecherche(String id) {
		log.debug("========== Service delRecherche  : " + id);
		Recherche recherche = rechercheRepo.findById(Long.valueOf(id)).orElse(null);
		if(null!=recherche) {
			log.debug( "==== delRecherche : " + recherche.toString());
			rechercheRepo.delete(recherche);
			Historique h = new Historique(recherche.getId(), " --- DELETE --- ");
			historiqueRepo.save(h);
			return new AngularRechercheDto(recherche);
		}else {
			return null;
		}
	}
}
