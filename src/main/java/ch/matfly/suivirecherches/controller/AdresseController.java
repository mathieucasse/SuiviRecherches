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
//import ch.matfly.suivirecherches.dao.AdresseRepo;
//import ch.matfly.suivirecherches.model.Adresse;
//
//@RestController
//public class AdresseController {
//	
//	@Autowired
//	AdresseRepo adresseRepo;
//	
//
//	@GetMapping(value="/adresses")
//	public List<Adresse> getAdresses() {
//		return adresseRepo.findAll();
//	}
//	
////	@RequestMapping("/getAlienOld")
////	public ModelAndView getAlienOld(@RequestParam Integer aid) {
////		ModelAndView mv = new ModelAndView("showAlien.jsp");
////		Alien  alien = alienRepo.findById(aid).orElse(new Alien());
////		mv.addObject(alien);
////		System.out.println("adding : " + alien.toString());
////		System.out.println("fetching : ");
////		System.out.println(alienRepo.findByTech("java"));
////		System.out.println(alienRepo.findByAidGreaterThan(102));
////		System.out.println(alienRepo.findByTechSorted("java"));
////		
////		return mv;
////	}
////	
//	@PostMapping("/adresse")
//	public Adresse addAdresse(@RequestBody Adresse adresse) {
//		adresseRepo.save(adresse);
//		return adresse;
//	}
//	
//	@PutMapping("/adresse")
//	public Adresse saveOrUpdateAdresse(@RequestBody Adresse adresse) {
//		adresseRepo.save(adresse);
//		return adresse;
//	}
//}
