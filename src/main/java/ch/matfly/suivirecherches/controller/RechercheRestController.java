package ch.matfly.suivirecherches.controller;

import ch.matfly.suivirecherches.model.dto.AngularRechercheDto;
import ch.matfly.suivirecherches.service.RechercheService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

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

	@GetMapping(value="recherches")
	@ApiOperation(value = "Get All Recherches",	notes = "Return a list of AngularRechercheDTO", response = List.class)
	public List<AngularRechercheDto> getRecherches() {
		return rechercheService.getRecherches();
	}

	@PutMapping("recherche")
	@ResponseBody
	@ApiOperation(value = "update a recherche",	notes = "update a recherche from an AngularRechercheDTO", response = AngularRechercheDto.class)
	public ResponseEntity<AngularRechercheDto> updateRecherche(@RequestBody AngularRechercheDto aRecherche) throws URISyntaxException, ParseException {

		log.debug(">>>>>>>>>  RestController==== updateRecherche Called !!! ");
		AngularRechercheDto recherche = rechercheService.updateRecherche(aRecherche);
		if(null!=recherche) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(recherche.getId())
					.toUri();
			return ResponseEntity.ok().body(recherche);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("recherche")
	@ResponseBody
	@ApiOperation(value = "add a recherche",	notes = "add a recherche from an AngularRechercheDTO", response = AngularRechercheDto.class)
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

	@PostMapping("recherche/event")
	@ResponseBody
	@ApiOperation(value = "add an event to a recherche",	notes = "add an event to recherche", response = AngularRechercheDto.class)
	public ResponseEntity<AngularRechercheDto> addRechercheEvent(@RequestBody AngularRechercheDto aRecherche) throws URISyntaxException, ParseException {

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

	@DeleteMapping("recherche/{id}")
	@ApiOperation(value = "delete a recherche",	notes = "delete a recherche from an id", response = AngularRechercheDto.class)
	public ResponseEntity<AngularRechercheDto> delRecherche(@PathVariable String id) {
		log.debug("========== delRecherche  : " + id);
		AngularRechercheDto recherche = rechercheService.delRecherche(id);
		if(null!=recherche) {
			log.debug( "==== delRecherche : " + recherche.toString());
			return ResponseEntity.accepted().body(recherche);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
