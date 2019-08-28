package ch.matfly.suivirecherches.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

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

import ch.matfly.suivirecherches.dao.HistoriqueRepo;
import ch.matfly.suivirecherches.dao.RechercheRepo;
import ch.matfly.suivirecherches.dao.RechercheStatutRepo;
import ch.matfly.suivirecherches.model.Historique;
import ch.matfly.suivirecherches.model.Recherche;
import ch.matfly.suivirecherches.model.RechercheStatut;
import ch.matfly.suivirecherches.util.AngularRecherche;
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

	private static final String STATUT_RECHERCHE = "statutRecherche";

	@Autowired
	private RechercheRepo rechercheRepo;

	@Autowired
	private RechercheStatutRepo rechercheStatutRepo;

	@Autowired
	private HistoriqueRepo historiqueRepo;

	private List<String> statutRecherche;
	public List<String> getStatutRecherche() {
		if(statutRecherche == null) {
			this.statutRecherche = rechercheStatutRepo.findAll().stream().map(RechercheStatut::getValue).collect(Collectors.toList());
		}
		return statutRecherche;
	}

	@GetMapping(value="recherches")
	public List<AngularRecherche> getRecherches() {
		return rechercheRepo.findAll().stream().map(AngularRecherche::new).collect(Collectors.toList());
	}

	@PostMapping("updateRecherche")
	@ResponseBody
	public ResponseEntity<AngularRecherche> updateRecherche(@RequestBody AngularRecherche aRecherche) throws URISyntaxException, ParseException {

		log.info(">>>>>>>>>  RestController==== updateRecherche Called !!! ");
		Recherche recherche = rechercheRepo.findById(aRecherche.getId()).orElse(null);

		if(null!=recherche) {
			log.info(">>>>>>>>>  RestController==== updateRecherche ==  " + recherche);
			recherche.updateWith(aRecherche);
			log.info(">>>>>>>>>  RestController==== updateRecherche ==  " + recherche);
			recherche = rechercheRepo.save(recherche);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(recherche.getId())
					.toUri();
			return ResponseEntity.created(uri)
					.body(new AngularRecherche(recherche));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("addRecherche")
	@ResponseBody
	public ResponseEntity<AngularRecherche>  addRecherche(@RequestBody AngularRecherche aRecherche) throws URISyntaxException, ParseException {
		Recherche recherche = aRecherche.buildRecherche();
		recherche = rechercheRepo.save(recherche);
		if(null != recherche.getId()) {
			log.info("addRecherche : " + recherche.toString());
			historiqueRepo.save(new Historique(recherche.getId(), " --- CREATE --- "));
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(recherche.getId())
					.toUri();
			return ResponseEntity.created(uri)
					.body(new AngularRecherche(recherche));
		}else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("delRecherche/{id}")
	public ResponseEntity<AngularRecherche> delRecherche(@PathVariable String id) {
		log.info("========== delRecherche  : " + id);
		Recherche recherche = rechercheRepo.findById(Long.valueOf(id)).orElse(null);
		if(null!=recherche) {
			log.info("delRecherche : " + recherche.toString());
			log.info( "==== delRecherche : " + recherche.toString());
			
			rechercheRepo.delete(recherche);
			Historique h = new Historique(recherche.getId(), " --- DELETE --- ");
			historiqueRepo.save(h);
			return ResponseEntity.ok()
					.body(new AngularRecherche(recherche));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}	
