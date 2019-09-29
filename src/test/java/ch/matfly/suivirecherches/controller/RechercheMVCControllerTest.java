package ch.matfly.suivirecherches.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RechercheMVCControllerTest {

	@Autowired
	private MockMvc mockMvc;

	 
//	{GET [/, /home]}: home()
	@Test
	public void shouldReturnHome() throws Exception {
		this.mockMvc.perform(get("/home"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("home.jsp"))
				.andExpect(model().size(2))
				.andExpect(model().attributeExists("recherches"))
				.andExpect(model().attributeExists("statutRecherche"))
               ;
	}
	
	@Test
    public void shouldReturnDefaultHome() throws Exception {
        this.mockMvc.perform(get("/"))
        		.andDo(print())
        		.andExpect(status().isOk())
        		.andExpect(view().name("home.jsp"))
        		.andExpect(model().size(2))
				.andExpect(model().attributeExists("recherches"))
				.andExpect(model().attributeExists("statutRecherche"))
        	;
    }

//	{GET /rechercheForm}: rechercheForm()
	@Test
    public void shouldReturnRechercheForm() throws Exception {
        this.mockMvc.perform(get("/rechercheForm"))
        		.andDo(print())
        		.andExpect(status().isOk())
        		.andExpect(view().name("rechercheForm.jsp"))
        		.andExpect(model().size(1))
				.andExpect(model().attributeExists("statutRecherche"))
        	;
    }
	
//	{POST /addRecherche}: addRecherche(String,String,String,String,String,String,String,String)
	@Test
    public void shouldReturnAddRecherche() throws Exception {
        this.mockMvc.perform(post("/addRecherche")
        						.param("recherche.creationDate" , "01/10/2010" )
        						.param("recherche.poste" , "recherche.poste" )
        						.param("recherche.client" , "recherche.client" )
        						.param("recherche.statut", "recherche.statut")
        						.param("personne.nom"    , "personne.nom"    )
        						.param("personne.prenom" , "personne.prenom" )
        						.param("personne.tel"    , "personne.tel"    )
        						.param("personne.email"  , "personne.email"  )
        						.param("entreprise.nom"  , "entreprise.nom"  )
        						.param("entreprise.tel"  , "entreprise.tel"  ))
        		.andDo(print())
        		.andExpect(status().isFound())
				.andExpect(redirectedUrl("home"))
        	;
    }

//	{GET /editRecherche}: editRecherche(String,List)
	@Test
    public void shouldEditRecherche() throws Exception {
//		Adresse adresse = new Adresse("rue 1", "ville 1", "cp 1");
//		Entreprise entreprise = new Entreprise("nom 1", "prenom 1", adresse);
//		Personne personne = new Personne("nom 1", "prenom 1", "telephone 1", "email 1");
//		Recherche recherche = new Recherche(new Date(), "poste 1","client 1", "KO",entreprise,personne);
//		recherche.setId(Long.valueOf("1"));
//		System.out.println("================ recherche = " +recherche);
//		Recherche recherche2 = new Recherche(new Date(), "poste 2","client 2", "EN_COURS",entreprise,personne);
//		recherche2.setId(Long.valueOf("2"));
//		System.out.println("================ recherche2 = " +recherche2);
//		List<Recherche> lr = new ArrayList<>();
//		lr.add(recherche); lr.add(recherche2);
//		
//        this.mockMvc.perform(get("/editRecherche").param("id" , "2" ).sessionAttr("recherches", lr))
//        		.andDo(print())
//        		.andExpect(status().isOk())
//        		.andExpect(view().name("rechercheEdit.jsp"))
//        		.andExpect(model().size(3))
//				.andExpect(model().attributeExists("recherches"))
//				.andExpect(model().attributeExists("recherche"))
//				.andExpect(model().attributeExists("statutRecherche"))
//        	;
    }
	//	{GET /showHistory}: showHistory(String,String,String)
	@Test
    public void shouldShowHistory() throws Exception {
        this.mockMvc.perform(get("/showHistory").param("id" , "2" ).param("entreprise" , "2" ).param("poste" , "2" ))
        		.andDo(print())
        		.andExpect(status().isOk())
        		.andExpect(view().name("showHistory.jsp"))
        		.andExpect(model().size(3))
				.andExpect(model().attributeExists("historique"))
				.andExpect(model().attributeExists("entreprise"))
				.andExpect(model().attributeExists("poste"))
        	;
    }
//	{POST /updateRecherche}: updateRecherche(Map)
	@Test
    public void shouldUpdateRecherche() throws Exception {
		
        this.mockMvc.perform(post("/updateRecherche").param("id" , "2" )
								        		.param("recherche.poste" , "recherche.poste" )
								        		.param("recherche.client" , "recherche.client" )
												.param("recherche.statut", "recherche.statut")
												.param("personne.nom"    , "personne.nom"    )
												.param("personne.prenom" , "personne.prenom" )
												.param("personne.tel"    , "personne.tel"    )
												.param("personne.email"  , "personne.email"  )
												.param("entreprise.nom"  , "entreprise.nom"  )
												.param("entreprise.tel"  , "entreprise.tel"  ))
			        		.andDo(print())
			        		.andExpect(status().isFound())
							.andExpect(redirectedUrl("home"))
        	;
    }

//	{ /delRecherche}: delRecherche(String)
	@Test
    public void shouldDeleteRecherche() throws Exception {
        this.mockMvc.perform(get("/delRecherche").param("id" , "1" ))
        		.andDo(print())
        		.andExpect(status().isFound())
				.andExpect(redirectedUrl("home"))
        	;
    }
}
