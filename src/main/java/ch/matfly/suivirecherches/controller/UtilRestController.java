package ch.matfly.suivirecherches.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.matfly.suivirecherches.service.ServiceStaticLists;


@RestController
@RequestMapping("rest")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4202"}, maxAge = 3600)
public class UtilRestController {
	
	@Autowired ServiceStaticLists  serviceStaticLists;
	
	@GetMapping(value="approcheMedia")
	public List<String> getApprocheMedia() {
		return serviceStaticLists.getApprocheMedia();
	}
	
	@GetMapping(value="assignationOrp")
	public List<String> getAssignationOrp() {
		return serviceStaticLists.getAssigantionOrp();
	}
	
	@GetMapping(value="rechercheStatut")
	public List<String> getRechercheStatut() {
		return serviceStaticLists.getRechercheStatut();
	}
	
	@GetMapping(value="tauxActivite")
	public List<String> getTauxActivite() {
		return serviceStaticLists.getTauxActivite();
	}
	
}
