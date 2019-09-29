package ch.matfly.suivirecherches.controller;

import ch.matfly.suivirecherches.model.Recherche;
import ch.matfly.suivirecherches.model.dto.AngularRechercheDto;
import ch.matfly.suivirecherches.service.RechercheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class RechercheRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RechercheService rechercheServiceMock;

	public Recherche rechercheForDTO = rechercheBuilder();
	public AngularRechercheDto recherche = angularRecherceBuilder();

	static private List<Recherche> recherchesForDTO;
	static private List<AngularRechercheDto> recherches;
	static {
		recherchesForDTO = new ArrayList<>();
		recherchesForDTO.add(new Recherche("AAA", "A"));
		recherchesForDTO.add(new Recherche("AAB", "A"));
		recherchesForDTO.add(new Recherche("ABB", "A"));
		recherchesForDTO.add(new Recherche("BBB", "B"));
		recherchesForDTO.add(new Recherche("BBA", "B"));
		recherchesForDTO.add(new Recherche("CCC", "C"));
		recherches = recherchesForDTO.stream()
				.map(r -> new AngularRechercheDto(r.getPoste(),r.getStatut())).collect(Collectors.toList());
	}

	@Test
	public void shouldGetAllRecheches() throws Exception {

		log.info("recherches = " + recherches );
		String result = this.toJsonString(recherches);
		when(rechercheServiceMock.getRecherches()).thenReturn(recherches);

		mockMvc.perform(get("/rest/recherches"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(result));
	}

	@Test
	public void shouldGetAllRechechesById() throws Exception {

		String result = this.toJsonString(recherches);
		when(rechercheServiceMock.getRecherchesByUserId("1")).thenReturn(recherches);

		mockMvc.perform(get("/rest/recherches/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(result));
	}

	@Test
	public void shouldUpdateRecherche() throws Exception {
		AngularRechercheDto rech = angularRecherceBuilder();
		rech.setId(1l);
		String result = this.toJsonString(rech);

		when(rechercheServiceMock.updateRecherche(any(AngularRechercheDto.class))).thenReturn(rech);

		mockMvc.perform(put("/rest/recherche")
				.content(result)
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(header().string("Location", "http://localhost/rest/recherche/1"))
				.andExpect(content().json(result));
	}



	@Test
	public void shouldAddRecherche() throws Exception {
		AngularRechercheDto rech = angularRecherceBuilder();
		rech.setId(1l);
		String result = this.toJsonString(rech);

		when(rechercheServiceMock.addRecherche(rech)).thenReturn(rech);

		mockMvc.perform(post("/rest/recherche")
				.content(result)
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(header().string("Location", "http://localhost/rest/recherche/1"))
				.andExpect(content().json(result));
	}

	@Test
	public void shouldDeleteRecherche() throws Exception {
		AngularRechercheDto rech = angularRecherceBuilder();
		rech.setId(1l);
		String result = this.toJsonString(rech);

		when(rechercheServiceMock.delRecherche(rech.getId().toString())).thenReturn(rech);
		this.mockMvc.perform(delete("/rest/recherche/1"))
				.andDo(print())
				.andExpect(status().isAccepted())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().json(result));
	}

	private <T> String toJsonString(T lu) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(lu);
	}

	private Recherche rechercheBuilder(){
		return new Recherche("Web Dev","EN COURS");
	}

	private AngularRechercheDto angularRecherceBuilder(){
		return new AngularRechercheDto("Web Dev","EN COURS");
	}
}
