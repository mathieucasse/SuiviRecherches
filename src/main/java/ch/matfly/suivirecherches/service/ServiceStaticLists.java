package ch.matfly.suivirecherches.service;

import ch.matfly.suivirecherches.dao.*;
import ch.matfly.suivirecherches.model.*;
import ch.matfly.suivirecherches.util.StaticLists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceStaticLists {

	@Autowired ApprocheMediaRepo approcheMediaRepo;
	@Autowired AssignationOrpRepo assignationOrpRepo;
	@Autowired RechercheStatutRepo rechercheStatutRepo;
	@Autowired TauxActiviteRepo tauxActiviteRepo;
	@Autowired RoleRepo roleRepo;

	private List<String> approcheMedia = new ArrayList<>();
	private List<String> assignationOrp = new ArrayList<>();
	private List<String> rechercheStatut = new ArrayList<>();
	private List<String> tauxActivite = new ArrayList<>();
	private List<Role> roles = new ArrayList<>();

	public List<String> getApprocheMedia() {
		if(this.approcheMedia.isEmpty()) {
			this.approcheMedia =  approcheMediaRepo.findAll().stream().map(ApprocheMedia::getValue).collect(Collectors.toList());
		}
		return this.approcheMedia;
	}

	public List<String> getAssigantionOrp() {
		if(this.assignationOrp.isEmpty()) {
			this.assignationOrp = assignationOrpRepo.findAll().stream().map(AssignationOrp::getValue).collect(Collectors.toList());
		}
		return this.assignationOrp;
	}

	public List<String> getRechercheStatut() {
		if(this.rechercheStatut.isEmpty()) {
			this.rechercheStatut = rechercheStatutRepo.findAll().stream().map(RechercheStatut::getValue).collect(Collectors.toList());
		}
		return this.rechercheStatut;
	}

	public List<String> getTauxActivite() {
		if(this.tauxActivite.isEmpty()) {
			this.tauxActivite = tauxActiviteRepo.findAll().stream().map(TauxActivite::getValue).collect(Collectors.toList());
		}
		return this.tauxActivite;
	}

	public List<String> getRolesNames() {
		return this.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
	}

	public List<Role> getRoles() {
		if(this.roles.isEmpty()) {
			this.roles = roleRepo.findAll();
			StaticLists.setRoles(this.roles);
		}
		return this.roles;
	}

	public void initLists() {

		log.info("Initializing static lists ....");

		if(approcheMediaRepo.count() < 1) {
			log.info("Loading ApprocheMedia List ");
			approcheMediaRepo.save(ApprocheMedia.of(1,"Ecrit"));    
			approcheMediaRepo.save(ApprocheMedia.of(2,"Visite"));   
			approcheMediaRepo.save(ApprocheMedia.of(3,"Telephone"));
		}

		if(assignationOrpRepo.count() < 1) {
			log.info("Loading AssignationOrp List ");
			assignationOrpRepo.save(AssignationOrp.of(0,"Non"));
			assignationOrpRepo.save(AssignationOrp.of(1,"Oui"));

		}

		if(rechercheStatutRepo.count() < 1) {
			log.info("Loading RechercheStatut List ");
			rechercheStatutRepo.save(RechercheStatut.of(1, "EN_COURS","En Cours"));
			rechercheStatutRepo.save(RechercheStatut.of(2, "ENTRETIEN","Entretien"));
			rechercheStatutRepo.save(RechercheStatut.of(3, "KO","Ko"));
			rechercheStatutRepo.save(RechercheStatut.of(4, "CANDIDATURE","Candidature"));
		}

		if(tauxActiviteRepo.count() < 1) {
			log.info("Loading TauxActivite List ");
			tauxActiviteRepo.save(TauxActivite.of(10,"10"));
			tauxActiviteRepo.save(TauxActivite.of(20,"20"));
			tauxActiviteRepo.save(TauxActivite.of(30,"30"));
			tauxActiviteRepo.save(TauxActivite.of(40,"40"));
			tauxActiviteRepo.save(TauxActivite.of(50,"50"));
			tauxActiviteRepo.save(TauxActivite.of(60,"60"));
			tauxActiviteRepo.save(TauxActivite.of(70,"70"));
			tauxActiviteRepo.save(TauxActivite.of(80,"80"));
			tauxActiviteRepo.save(TauxActivite.of(90,"90"));
			tauxActiviteRepo.save(TauxActivite.of(100,"100"));
		}

		if(roleRepo.count() < 1) {
			log.info("Loading Roles List ");
			roleRepo.save(Role.of(1L,Role.USER,"Standard User - Has no admin rights"));
			roleRepo.save(Role.of(2L,Role.ADMIN,"Admin User - Has permission to perform admin tasks"));
		}
	}
}
