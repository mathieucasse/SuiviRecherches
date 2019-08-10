package ch.matfly.suivirecherches;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ch.matfly.suivirecherches.dao.AdresseRepo;
import ch.matfly.suivirecherches.dao.EntrepriseRepo;
import ch.matfly.suivirecherches.model.Adresse;
import ch.matfly.suivirecherches.model.Entreprise;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHybernateApplicationTests {
	
	@Autowired
	private AdresseRepo adresseRepo;
	
	@Autowired
	private EntrepriseRepo entrepriseRepo;

	@Test
	public void contextLoads() {
		
		Adresse adresse1 = new Adresse("rue 1", "ville 1", "cp 1");
		Entreprise personne1 = new Entreprise("nom 1", "prenom 1", adresse1);
		
		personne1 = entrepriseRepo.save(personne1);
		int i =0;
		System.out.println(" ======== "+ i++ +" ======="+personne1);
		
		Entreprise p = entrepriseRepo.findById(personne1.getId()).orElse(null);
		System.out.println(" ======== "+ i++ +" ======="+p);
		
		Adresse a = p.getAdresse();
		System.out.println(" ======== "+ i++ +" ======="+a);
		adresseRepo.delete(a);
		adresseRepo.flush();
		System.out.println(" ======== "+ i++ +" ======="+p);
		
		a = adresseRepo.findById(a.getId()).orElse(null);
		System.out.println(" ======== "+ i++ +" ======="+a);
		assertNotNull("Shoul not be null !!", a);
		
//		p.setAdresse(null);
//		adresseRepo.delete(a);
//		System.out.println(" ======== "+ i++ +" ======="+p);
//		
//		p = entrepriseRepo.save(p);
		
		p = entrepriseRepo.findById(p.getId()).orElse(null);
		System.out.println(" ======== "+ i++ +" ======="+p);
		
		a = adresseRepo.findById(a.getId()).orElse(null);
		System.out.println(" ======== "+ i++ +" ======="+a);
	}

}
