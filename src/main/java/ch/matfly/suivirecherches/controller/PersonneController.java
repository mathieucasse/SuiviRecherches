//package ch.matfly.suivirecherches.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import ch.matfly.suivirecherches.dao.PersonneRepo;
//import ch.matfly.suivirecherches.model.Personne;
//
//@RestController
//public class PersonneController {
//
//	@Autowired
//	PersonneRepo personneRepo;
//	
//	@GetMapping(value="/personnes")
//	public List<PersonneAudit> getPersonnes() {
//		return personneRepo.findAll();
//	}
//	
//	@PostMapping("/personne")
//	public PersonneAudit addPersonne(@RequestBody PersonneAudit personne) {
//		personneRepo.save(personne);
//		return personne;
//	}
//	
//	@PutMapping("/personne")
//	public PersonneAudit saveOrUpdatePersonne(@RequestBody PersonneAudit personne) {
//		personneRepo.save(personne);
//		return personne;
//	}
//	
//}
