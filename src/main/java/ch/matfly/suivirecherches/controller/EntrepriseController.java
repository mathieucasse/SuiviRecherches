//package ch.matfly.suivirecherches.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import ch.matfly.suivirecherches.dao.EntrepriseRepo;
//import ch.matfly.suivirecherches.model.Entreprise;
//
//@RestController
//public class EntrepriseController {
//
//	@Autowired
//	EntrepriseRepo entrepriseRepo;
//	
//	@GetMapping(value="/entreprises")
//	public List<Entreprise> getEntreprises() {
//		return entrepriseRepo.findAll();
//	}
//	
//	@PostMapping("/entreprise")
//	public Entreprise addEntreprise(@RequestBody Entreprise entreprise) {
//		entrepriseRepo.save(entreprise);
//		return entreprise;
//	}
//	
//	@PutMapping("/entreprise")
//	public Entreprise saveOrUpdateEntreprise(@RequestBody Entreprise entreprise) {
//		entrepriseRepo.save(entreprise);
//		return entreprise;
//	}
//}
