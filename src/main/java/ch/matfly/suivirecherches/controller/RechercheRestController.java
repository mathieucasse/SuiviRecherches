package ch.matfly.suivirecherches.controller;

import ch.matfly.suivirecherches.model.Recherche;
import ch.matfly.suivirecherches.model.User;
import ch.matfly.suivirecherches.model.dto.AngularRechercheDto;
import ch.matfly.suivirecherches.service.RechercheService;
import ch.matfly.suivirecherches.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author mathieucasse
 *
 */
@RestController
@Slf4j
@RequestMapping("rest")
@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
public class RechercheRestController {

	@Autowired private RechercheService rechercheService;
	@Autowired private UserService userService;

	@GetMapping(value="recherches")
	@ApiOperation(value = "Get All Recherches",
			notes = "Return a list of AngularRechercheDTO",
			response = List.class)
	public List<AngularRechercheDto> getRecherches() {
		return rechercheService.getRecherches();
	}

	@GetMapping(value="recherches/{email}")
	@ApiOperation(value = "Get All Recherches By User email",	notes = "Return for a User the list of AngularRechercheDTO ", response = List.class)
	public List<AngularRechercheDto> getRecherchesByUserEmail(@PathVariable String email) {
		User user = userService.getUserByEmail(email);
		log.debug("=== getRecherchesByUser ( " + user + " , " + email +" )" );
		return rechercheService.getRecherchesByUser(user);
	}

	@PutMapping("recherche")
	@ResponseBody
	@ApiOperation(value = "update a recherche",
			notes = "update a recherche from an AngularRechercheDTO",
			response = AngularRechercheDto.class)
	public ResponseEntity<AngularRechercheDto> updateRecherche(@RequestBody AngularRechercheDto aRecherche) {

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

	@PostMapping("recherche")
	@ResponseBody
	@ApiOperation(value = "add a recherche",
			notes = "add a recherche from an AngularRechercheDTO",
			response = AngularRechercheDto.class)
	public ResponseEntity<AngularRechercheDto> addRecherche(@RequestBody AngularRechercheDto aRecherche) {
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
			return ResponseEntity.unprocessableEntity().build();
		}

	}

	@DeleteMapping("recherche/{id}")
	@ApiOperation(value = "delete a recherche",
			notes = "delete a recherche from an id",
			response = AngularRechercheDto.class)
	public ResponseEntity<AngularRechercheDto> delRecherche(@PathVariable String id) {
		log.debug("========== delRecherche  : " + id);
		Recherche checkedRecherche = rechercheService.getRechercheByIdAndUser(id);
		if(null != checkedRecherche) {
			AngularRechercheDto recherche = rechercheService.delRecherche(id);
			log.debug( "==== delRecherche : " + recherche.toString());
			return ResponseEntity.accepted().body(recherche);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
