package ch.matfly.suivirecherches.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.matfly.suivirecherches.dao.PersonneRepo;
import ch.matfly.suivirecherches.model.Personne;

@Controller
public class PersonneMVCController {
	
	@Autowired
	PersonneRepo personneRepo;
	
//	@RequestMapping("/home")
//	public String home() {
//		return "home.jsp";
//	}
	@RequestMapping("personnes")
	public ModelAndView indexPersonnes() {
		ModelAndView mv = new ModelAndView("indexPersonnes.jsp");
		List<Personne> personnes = personneRepo.findAll();
//		PersonneAudit personnes = personneRepo.findById(3l).orElse(new PersonneAudit());
		mv.addObject("personnes",personnes);
		System.out.println("adding : " + personnes.toString());
		System.out.println("fetching : ");
		return mv;
	}

}
