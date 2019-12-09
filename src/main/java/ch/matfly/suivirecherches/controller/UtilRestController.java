package ch.matfly.suivirecherches.controller;

import ch.matfly.suivirecherches.service.ServiceStaticLists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest")
@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
public class UtilRestController {
	
	@Autowired ServiceStaticLists  serviceStaticLists;
	
	@GetMapping(value="approcheMedia")
	@ApiOperation(value = "Get All Possible Values for approcheMedia", response = List.class)
	public List<String> getApprocheMedia() {
		return serviceStaticLists.getApprocheMedia();
	}
	
	@GetMapping(value="assignationOrp")
	@ApiOperation(value = "Get All Possible Values for assignationOrp", response = List.class)
	public List<String> getAssignationOrp() {
		return serviceStaticLists.getAssigantionOrp();
	}
	
	@GetMapping(value="rechercheStatut")
	@ApiOperation(value = "Get All Possible Values for rechercheStatut", response = List.class)
	public List<String> getRechercheStatut() {
		return serviceStaticLists.getRechercheStatut();
	}
	
	@GetMapping(value="tauxActivite")
	@ApiOperation(value = "Get All Possible Values for tauxActivite", response = List.class)
	public List<String> getTauxActivite() {
		return serviceStaticLists.getTauxActivite();
	}

	@GetMapping(value="roles")
	@ApiOperation(value = "Get All Possible Values for role", response = List.class)
	public List<String> getRoles() {
		return serviceStaticLists.getRolesNames();
	}
}
