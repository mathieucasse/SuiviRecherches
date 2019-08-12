//package ch.matfly.suivirecherches;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import ch.matfly.suivirecherches.dao.AdresseRepo;
//import ch.matfly.suivirecherches.dao.EntrepriseRepo;
//import ch.matfly.suivirecherches.dao.PersonneRepo;
//import ch.matfly.suivirecherches.dao.RechercheRepo;
//import ch.matfly.suivirecherches.model.Adresse;
//import ch.matfly.suivirecherches.model.Entreprise;
//import ch.matfly.suivirecherches.model.Personne;
//import ch.matfly.suivirecherches.model.Recherche;
//
//@Component
//public class SomeTests implements CommandLineRunner{
//
//	@Autowired
//	private AdresseRepo adresseRepo;
//	
//	@Autowired
//	private EntrepriseRepo entrepriseRepo;
//	
//	@Autowired
//	private PersonneRepo personneRepo;
//	
//	@Autowired
//	private RechercheRepo rechercheRepo;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		Adresse adresse1 = new Adresse("rue 1", "ville 1", "cp 1");
//		Entreprise personne1 = new Entreprise("nom 1", "prenom 1", adresse1);
//		
//		personne1 = entrepriseRepo.save(personne1);
//		int i =0;
//		System.out.println(" ======== "+ i++ +" ======="+personne1);
//		
//		Entreprise e = entrepriseRepo.findById(personne1.getId()).orElse(null);
//		System.out.println(" ======== "+ i++ +" ======="+e);
//		
//		Adresse a = e.getAdresse();
//		System.out.println(" ======== "+ i++ +" ======="+a);
//		adresseRepo.delete(a);
//		adresseRepo.flush();
//		System.out.println(" ======== "+ i++ +" ======="+e);
//		
//		a = adresseRepo.findById(a.getId()).orElse(null);
//		System.out.println(" ======== "+ i++ +" ======="+a);
//		
////		e.setAdresse(null);
////		System.out.println(" ======== "+ i++ +" ======="+e);
////		
////		e = entrepriseRepo.save(e);
//		
//		e = entrepriseRepo.findById(e.getId()).orElse(null);
//		System.out.println(" ======== "+ i++ +" ======="+e);
//		
//		a = adresseRepo.findById(a.getId()).orElse(null);
//		System.out.println(" ======== "+ i++ +" ======="+a);
//		
//		
//		Personne personne = new Personne("nom 1", "prenom 1", "telephone 1", "email 1");
//		personne = personneRepo.save(personne);
//		System.out.println(" ======== "+ i++ +" ======="+personne);	
//		
//		Recherche recherche = new Recherche( "poste 1", "KO");
//		System.out.println(" ======== "+ i++ +" ======="+recherche);
//		recherche = rechercheRepo.save(recherche);
//		System.out.println(" ======== "+ i++ +" ======="+recherche);
//		recherche.setEntreprise(e);
//		recherche.setPersonne(personne);
//		System.out.println(" ======== "+ i++ +" ======="+recherche);
//		recherche = rechercheRepo.save(recherche);
//		System.out.println(" ======== "+ i++ +" ======="+recherche);
//		
//	}
//
//}
