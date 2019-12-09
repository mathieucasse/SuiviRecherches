package ch.matfly.suivirecherches.controller;

import ch.matfly.suivirecherches.dao.AuditRepo;
import ch.matfly.suivirecherches.model.dto.EntrepriseAuditDto;
import ch.matfly.suivirecherches.model.dto.EvenementAuditDto;
import ch.matfly.suivirecherches.model.dto.PersonneAuditDto;
import ch.matfly.suivirecherches.model.dto.RechercheAuditDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("rest")
@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
public class AuditRestController {

    @Autowired
    private AuditRepo auditRepo;

    @GetMapping("audit/recherche/{id}")
	public ResponseEntity<List<RechercheAuditDto>> getRechercheAudit(@PathVariable String id) {
		log.debug("========== auditRecherche  : " + id);
		List<RechercheAuditDto> recherches = auditRepo.getRechercheAuditRevForId(Long.valueOf(id));
		if(null!=recherches) {
			log.debug("Recherches : " + recherches.toString());
			return ResponseEntity.ok().body(recherches);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("audit/personne/{id}")
	public ResponseEntity<List<PersonneAuditDto>> getPersonneAudit(@PathVariable String id) {
		log.debug("========== auditPersonne  : " + id);
		List<PersonneAuditDto> personnes = auditRepo.getPersonneAuditRevForId(Long.valueOf(id));
		if(null!=personnes) {
			log.debug("Personnes : " + personnes.toString());
			return ResponseEntity.ok().body(personnes);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("audit/entreprise/{id}")
	public ResponseEntity<List<EntrepriseAuditDto>> getEntrepriseAudit(@PathVariable String id) {
		log.debug("========== auditEntreprise  : " + id);
		List<EntrepriseAuditDto> entreprises = auditRepo.getEntrepriseAuditRevForId(Long.valueOf(id));
		if(null!=entreprises) {
			log.debug("Entreprises : " + entreprises.toString());
			return ResponseEntity.ok().body(entreprises);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("audit/evenement/{id}")
	public ResponseEntity<List<EvenementAuditDto>> getEvenementAudit(@PathVariable String id) {
		log.debug("========== audit/evenement/  : " + id);
		List<EvenementAuditDto> evenements = auditRepo.getEvenementAuditRevForId(Long.valueOf(id));
		if(null!=evenements) {
			log.debug("Evenements : " + evenements.toString());
			return ResponseEntity.ok().body(evenements);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
