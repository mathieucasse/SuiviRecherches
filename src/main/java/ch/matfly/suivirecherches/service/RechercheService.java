package ch.matfly.suivirecherches.service;

import ch.matfly.suivirecherches.dao.HistoriqueRepo;
import ch.matfly.suivirecherches.dao.RechercheRepo;
import ch.matfly.suivirecherches.model.Evenement;
import ch.matfly.suivirecherches.model.Historique;
import ch.matfly.suivirecherches.model.Recherche;
import ch.matfly.suivirecherches.model.User;
import ch.matfly.suivirecherches.model.dto.AngularRechercheDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RechercheService {

	@Autowired private RechercheRepo rechercheRepo;
	@Autowired private HistoriqueRepo historiqueRepo;
	@Autowired private UserService userService;

	public List<AngularRechercheDto> getRecherches() {
		return rechercheRepo.findAll().stream().map(AngularRechercheDto::new).collect(Collectors.toList());
	}
	public List<AngularRechercheDto> getRecherchesByUser(User user) {
		return rechercheRepo.findAllByOwner(user).stream().map(AngularRechercheDto::new).collect(Collectors.toList());
	}

	public Recherche getRechercheByEvenement(Evenement evenement) {
		Optional<Recherche> recherche = rechercheRepo.findById(evenement.getRechercheId());
		if(recherche.isPresent()){
			return recherche.get();
		}else throw new NoResultException("Recherche with id = " + evenement.getRechercheId() + " not found");
	}

	public Recherche getRechercheByIdAndUser(String id) {
		Optional<Recherche> optionalRecherche = rechercheRepo.findById(Long.valueOf(id));
		if(optionalRecherche.isPresent()){
			Recherche recherche = optionalRecherche.get();
			if(userService.getUser().getId().equals(recherche.getOwner().getId())) {
				return recherche;
			}else throw new NoResultException("Recherche with user : " + userService.getUser() + " not found");
		}else throw new NoResultException("Recherche with id = " + id + " not found");
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
	
	public AngularRechercheDto addRecherche(AngularRechercheDto aRecherche) {
		log.debug(">>>>>>>>>  Service ==== addRecherche Called !!! ");
		Recherche recherche = aRecherche.buildNewRecherche();
		User user = userService.getUser();
		if(null != user) {
			recherche.setOwner(user);
		}
		log.debug("========== addRecherche  : " + recherche);
		recherche = rechercheRepo.save(recherche);
		recherche.getEvenements().get(0).setRecherche(recherche);
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
		if(null != recherche) {
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
