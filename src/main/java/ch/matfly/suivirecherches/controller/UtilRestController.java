package ch.matfly.suivirecherches.controller;

import ch.matfly.suivirecherches.service.ServiceStaticLists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {
		"http://ec2-3-19-239-63.us-east-2.compute.amazonaws.com",
		"http://ec2-3-19-239-63.us-east-2.compute.amazonaws.com:4200",
		"http://localhost",
		"http://localhost:4200",
		"http://localhost:4202"}, maxAge = 3600)
@RestController
@RequestMapping("rest")
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
	
}
