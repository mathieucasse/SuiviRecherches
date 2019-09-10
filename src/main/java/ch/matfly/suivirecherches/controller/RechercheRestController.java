package ch.matfly.suivirecherches.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.matfly.suivirecherches.dao.AuditRepo;
import ch.matfly.suivirecherches.dao.HistoriqueRepo;
import ch.matfly.suivirecherches.model.Historique;
import ch.matfly.suivirecherches.model.Recherche;
import ch.matfly.suivirecherches.model.dto.AngularRechercheDto;
import ch.matfly.suivirecherches.model.dto.EntrepriseAuditDto;
import ch.matfly.suivirecherches.model.dto.PersonneAuditDto;
import ch.matfly.suivirecherches.model.dto.RechercheAuditDto;
import ch.matfly.suivirecherches.service.RechercheService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mathieucasse
 *
 */
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4202"}, maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("rest")
public class RechercheRestController {

	@Autowired private RechercheService rechercheService;
	@Autowired private AuditRepo auditRepo;


	@GetMapping(value="recherches")
	public List<AngularRechercheDto> getRecherches() {
		return rechercheService.getRecherches();
	}

	@PostMapping("updateRecherche")
	@ResponseBody
	public ResponseEntity<AngularRechercheDto> updateRecherche(@RequestBody AngularRechercheDto aRecherche) throws URISyntaxException, ParseException {

		log.debug(">>>>>>>>>  RestController==== updateRecherche Called !!! ");
		AngularRechercheDto recherche = rechercheService.updateRecherche(aRecherche);
		if(null!=recherche) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(recherche.getId())
					.toUri();
			return ResponseEntity.created(uri).body(recherche);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("addRecherche")
	@ResponseBody
	public ResponseEntity<AngularRechercheDto> addRecherche(@RequestBody AngularRechercheDto aRecherche) throws URISyntaxException, ParseException {
		log.debug("========== addRecherche  : " + aRecherche);
		AngularRechercheDto recherche = rechercheService.addRecherche(aRecherche);
		if(null != recherche.getId()) {
			log.debug("added Recherche : " + recherche.toString());
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(recherche.getId())
					.toUri();
			return ResponseEntity.created(uri).body(recherche);
		}else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("delRecherche/{id}")
	public ResponseEntity<AngularRechercheDto> delRecherche(@PathVariable String id) {
		log.debug("========== delRecherche  : " + id);
		AngularRechercheDto recherche = rechercheService.delRecherche(id);
		if(null!=recherche) {
			log.debug( "==== delRecherche : " + recherche.toString());
			return ResponseEntity.ok().body(recherche);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("auditRecherche/{id}")
	public ResponseEntity<List<RechercheAuditDto>> getRechercheAudit(@PathVariable String id) {
		log.debug("========== auditRecherche  : " + id);
		List<RechercheAuditDto> recherche = auditRepo.getRechercheAuditRevForId(Long.valueOf(id));
		if(null!=recherche) {
			log.debug("Recherche : " + recherche.toString());
			return ResponseEntity.ok().body(recherche);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("auditPersonne/{id}")
	public ResponseEntity<List<PersonneAuditDto>> getPersonneAudit(@PathVariable String id) {
		log.debug("========== auditPersonne  : " + id);
		List<PersonneAuditDto> personne = auditRepo.getPersonneAuditRevForId(Long.valueOf(id));
		if(null!=personne) {
			log.debug("Personne : " + personne.toString());
			return ResponseEntity.ok().body(personne);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("auditEntreprise/{id}")
	public ResponseEntity<List<EntrepriseAuditDto>> getEntrepriseAudit(@PathVariable String id) {
		log.debug("========== auditEntreprise  : " + id);
		List<EntrepriseAuditDto> entreprise = auditRepo.getEntrepriseAuditRevForId(Long.valueOf(id));
		if(null!=entreprise) {
			log.debug("Entreprise : " + entreprise.toString());
			return ResponseEntity.ok().body(entreprise);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}	
