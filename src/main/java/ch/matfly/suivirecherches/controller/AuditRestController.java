package ch.matfly.suivirecherches.controller;

import ch.matfly.suivirecherches.dao.AuditRepo;
import ch.matfly.suivirecherches.model.dto.EntrepriseAuditDto;
import ch.matfly.suivirecherches.model.dto.PersonneAuditDto;
import ch.matfly.suivirecherches.model.dto.RechercheAuditDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4202"}, maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("rest")
public class AuditRestController {

    @Autowired
    private AuditRepo auditRepo;

    @GetMapping("audit/recherche/{id}")
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

	@GetMapping("audit/personne/{id}")
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
	@GetMapping("audit/entreprise/{id}")
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
