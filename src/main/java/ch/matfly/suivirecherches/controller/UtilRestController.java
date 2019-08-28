package ch.matfly.suivirecherches.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.matfly.suivirecherches.dao.ApprocheMediaRepo;
import ch.matfly.suivirecherches.dao.AssignationOrpRepo;
import ch.matfly.suivirecherches.dao.RechercheStatutRepo;
import ch.matfly.suivirecherches.dao.TauxActiviteRepo;

@RestController
@RequestMapping("rest")
public class UtilRestController {
	
	@Autowired ApprocheMediaRepo approcheMediaRepo;
	
	@Autowired AssignationOrpRepo assignationOrpRepo;
	
	@Autowired RechercheStatutRepo rechercheStatutRepo;
	
	@Autowired TauxActiviteRepo tauxActiviteRepo; 
	
	
	private List<String> approcheMedia = new ArrayList<String>();
	private List<String> assignationOrp = new ArrayList<String>();
	private List<String> rechercheStatut = new ArrayList<String>();
	private List<String> tauxActivite = new ArrayList<String>();
	
	
	@CrossOrigin
	@GetMapping(value="approcheMedia")
	public List<String> getApprocheMedia() {
		if(this.approcheMedia.isEmpty()) {
			this.approcheMedia =  approcheMediaRepo.findAll().stream().map(l -> l.getValue()).collect(Collectors.toList());
		}
		return this.approcheMedia;
	}
	
	@CrossOrigin
	@GetMapping(value="assignationOrp")
	public List<String> getAssignationOrp() {
		if(this.assignationOrp.isEmpty()) {
			this.assignationOrp = assignationOrpRepo.findAll().stream().map(l -> l.getValue()).collect(Collectors.toList());
		}
		return this.assignationOrp;
	}
	
	@CrossOrigin
	@GetMapping(value="rechercheStatut")
	public List<String> getRechercheStatut() {
		if(this.rechercheStatut.isEmpty()) {
			this.rechercheStatut = rechercheStatutRepo.findAll().stream().map(l -> l.getValue()).collect(Collectors.toList());
		}
		return this.rechercheStatut;
	}
	
	@CrossOrigin
	@GetMapping(value="tauxActivite")
	public List<String> getTauxActivite() {
		if(this.tauxActivite.isEmpty()) {
			this.tauxActivite = tauxActiviteRepo.findAll().stream().map(l -> l.getValue()).collect(Collectors.toList());
		}
		return this.tauxActivite;
	}
	
}
