package ch.matfly.suivirecherches.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ch.matfly.suivirecherches.dao.HistoriqueRepo;
import ch.matfly.suivirecherches.dao.RechercheRepo;
import ch.matfly.suivirecherches.dao.RechercheStatutRepo;
import ch.matfly.suivirecherches.model.Entreprise;
import ch.matfly.suivirecherches.model.Historique;
import ch.matfly.suivirecherches.model.Personne;
import ch.matfly.suivirecherches.model.Recherche;
import ch.matfly.suivirecherches.model.RechercheStatut;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mathieucasse
 *
 */
@Controller
@Slf4j
@SessionAttributes("recherches")
public class RechercheMVCController {
	
	private static final String STATUT_RECHERCHE = "statutRecherche";

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	private RechercheRepo rechercheRepo;

	@Autowired
	private HistoriqueRepo historiqueRepo;
	
	@Autowired
	private RechercheStatutRepo rechercheStatutRepo;

	private List<String> statutRecherche;

	public List<String> getStatutRecherche() {
		if(statutRecherche == null) {
			this.statutRecherche = rechercheStatutRepo.findAll().stream().map(x -> x.getValue()).collect(Collectors.toList());
//			RechercheRestController.statutRecherche = Stream.of(RechercheStatut.values())
//					.map(Enum::name)
//					.collect(Collectors.toList());		
		}
		return statutRecherche;
	}

	@GetMapping(value= {"/","/home"})
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home.jsp");
		List<Recherche> recherches = rechercheRepo.findAll();
		mv.addObject("recherches", recherches);
		mv.addObject(STATUT_RECHERCHE, this.getStatutRecherche());
		return mv;
	}

	@GetMapping("/rechercheForm")
	public ModelAndView rechercheForm() {
		ModelAndView mv = new ModelAndView("rechercheForm.jsp");
		mv.addObject(STATUT_RECHERCHE, this.getStatutRecherche());
		return mv;
	}

	@PostMapping("/addRecherche")
	@ResponseBody
	public RedirectView addRecherche(
			@RequestParam(name = "recherche.creationDate") String creationDate,
			@RequestParam(name = "recherche.poste") String poste,
			@RequestParam(name = "recherche.client") String client,
			@RequestParam(name = "recherche.statut") String statut,
			@RequestParam(name = "personne.nom") String personneNom,
			@RequestParam(name = "personne.prenom") String personnePrenom,
			@RequestParam(name = "personne.tel") String personneTel,
			@RequestParam(name = "personne.email") String personneEmail,
			@RequestParam(name = "entreprise.nom") String entrepriseNom,
			@RequestParam(name = "entreprise.tel") String entrepriseTel
			) throws ParseException {
		Entreprise e = new Entreprise(entrepriseNom, entrepriseTel, null);
		
		Personne p = new Personne(personneNom, personnePrenom, personneTel, personneEmail);
		Recherche recherche = new Recherche(sdf.parse(creationDate),poste,client, statut,e,p);
		recherche=rechercheRepo.save(recherche);
		log.info("addRecherche : " + recherche.toString());
		historiqueRepo.save(new Historique(recherche.getId(), " --- CREATE --- "));
		return new RedirectView("home");
	}

	@GetMapping("/delRecherche")
	@ResponseBody
	public RedirectView delRecherche(@RequestParam(name = "id") String id) {
		Recherche recherche = rechercheRepo.findById(Long.valueOf(id)).orElse(null);
		if(null!=recherche) {
			log.info("delRecherche : " + recherche.toString());
			log.info( "==== delRecherche : " + recherche.toString());
			Historique h = new Historique(recherche.getId(), " --- DELETE --- ");
			historiqueRepo.save(h);
			rechercheRepo.delete(recherche);
		}
		return new RedirectView("home");
	}

	@GetMapping("/editRecherche")
	@ResponseBody
	public ModelAndView editRecherche(@RequestParam(name = "id") String id, @ModelAttribute("recherches") List<Recherche> recherches) {
		ModelAndView mv = new ModelAndView("rechercheEdit.jsp");
		log.info("==== editRecherche nb rech "+ recherches.size()+" : " + recherches.toString());
		log.info("==== editRecherche id "+ id );
		Recherche recherche = recherches.stream()
								.filter(rech -> Long.valueOf(id).equals(rech.getId()))
								.findAny().orElse(null);
		log.info("==== editRecherche : " + recherche.toString());
		mv.addObject("recherche",recherche);
		mv.addObject(STATUT_RECHERCHE, this.getStatutRecherche());
		return mv;
	}

	@GetMapping("/showHistory")
	@ResponseBody
	public ModelAndView showHistory(@RequestParam(name = "id") String id,
			@RequestParam(name = "entreprise") String entreprise,
			@RequestParam(name = "poste") String poste) {
		ModelAndView mv = new ModelAndView("showHistory.jsp");
		log.info("==== showAllHistory : " + historiqueRepo.findAll().toString());
		List<Historique> historique = historiqueRepo.findByRechercheId(Long.valueOf(id));
		log.info("==== showHistory : " + historique.toString());
		mv.addObject("historique",historique);
		mv.addObject("entreprise",entreprise);
		mv.addObject("poste",poste);
		return mv;
	}
	@PostMapping("/updateRecherche")
	@ResponseBody
	public RedirectView updateRecherche(@RequestParam Map<String, String> parameters) throws ParseException {

		log.info("==== updateRecherche Called !!! ");
		log.info("==== "+ parameters.size()+" !!! ");
		log.info(parameters.entrySet().stream()
				.map(e -> e.getKey() + " = " + e.getValue())
				.collect(Collectors.joining(", ")));

		String rp;
		String tmpRp;
		StringBuilder sb = new StringBuilder();
		if((tmpRp = lookFor("id", parameters))!=null){
			Recherche recherche = rechercheRepo.findById(Long.valueOf(tmpRp)).orElse(null);
			if(null!=recherche) {
				
				rp = "recherche.creationDate";
				if((tmpRp = lookForAndAudit(rp, parameters, sdf.format(recherche.getContactDate()), sb))!= null) {
					recherche.setContactDate(sdf.parse(tmpRp));
				}
				rp = "recherche.poste";
				if((tmpRp = lookForAndAudit(rp, parameters, recherche.getPoste(), sb))!= null) {
					recherche.setPoste(tmpRp);
				}
				rp = "recherche.client";
				if((tmpRp = lookForAndAudit(rp, parameters, recherche.getClient(), sb))!= null) {
					recherche.setClient(tmpRp);
				}
				rp = "recherche.statut";
				if((tmpRp = lookForAndAudit(rp, parameters, recherche.getStatut(), sb))!= null) {
					recherche.setStatut(tmpRp);
				}
				rp = "personne.nom";
				if((tmpRp = lookForAndAudit(rp, parameters, recherche.getPersonne().getNom(), sb))!= null) {
					recherche.getPersonne().setNom(tmpRp);
				}
				rp = "personne.prenom";
				if((tmpRp = lookForAndAudit(rp, parameters, recherche.getPersonne().getPrenom(),sb))!= null) {
					recherche.getPersonne().setPrenom(tmpRp);
				}
				rp = "personne.tel";
				if((tmpRp = lookForAndAudit(rp, parameters, recherche.getPersonne().getTelephone(),sb))!= null) {
					recherche.getPersonne().setTelephone(tmpRp);
				}
				rp = "personne.email";
				if((tmpRp = lookForAndAudit(rp, parameters, recherche.getPersonne().getEmail(),sb))!= null) {
					recherche.getPersonne().setEmail(tmpRp);
				}
				rp = "entreprise.nom";
				if((tmpRp = lookForAndAudit(rp, parameters, recherche.getEntreprise().getNom(),sb))!= null) {
					recherche.getEntreprise().setNom(tmpRp);
				}
				rp = "entreprise.tel";
				if((tmpRp = lookForAndAudit(rp, parameters, recherche.getEntreprise().getTelephone(),sb))!= null) {
					recherche.getEntreprise().setTelephone(tmpRp);
				}

				if(sb.length()>0) {
					rechercheRepo.save(recherche);
					sb.deleteCharAt(sb.length()-1);
					sb.deleteCharAt(sb.length()-1);
					historiqueRepo.save(new Historique(recherche.getId(), sb.toString()));
					log.info("================== Historique enregistre");
				}

			}
		}

		return new RedirectView("home");
	}
	
	@GetMapping("/test")
	public String test() {
		
		return "test.jsp";
	}
	
	static String lookFor(String rp, Map<String, String> map) {
		log.debug("======= Looking for " + rp);
		String ret = map.get(rp);
		return (null != ret)? ret:"-";
	}


	/**
	 * @param rp
	 * @param map
	 * @param valObj
	 * @return val to set or null if not modified
	 */
	
	// TODO  fix null values in valObj
	static String lookForAndAudit(String rp, Map<String, String> map, String valObj, StringBuilder sb ) {
		log.debug("=========== lookForAndAudit : rp = " + rp +" ,  map = " + map + " , valObj = " + valObj + " , sb = " + sb);
		String tmpRp = lookFor(rp, map);
		if(valObj == null) valObj = "";
		if(!valObj.equals(tmpRp)) {
			log.debug(rp + "changed from " + valObj + " to " + tmpRp);
			sb.append(rp + "[" + valObj + "==>" + tmpRp + "], ");
			return tmpRp;
		}
		return null;
	}
}	
