package ch.matfly.suivirecherches.controller;

import ch.matfly.suivirecherches.dao.UserRepo;
import ch.matfly.suivirecherches.model.Recherche;
import ch.matfly.suivirecherches.model.User;
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

	@MockBean
	private UserRepo userRepoMock;

//    @Autowired
//    private WebApplicationContext context;

//    @Before
//    public void setup() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }

	public Recherche rechercheForDTO = rechercheBuilder();
	public AngularRechercheDto recherche = angularRecherceBuilder();

	static private List<Recherche> recherchesForDTO;
	static private List<AngularRechercheDto> recherches;
	static private User user = new User("1","aaa@ppp.ch", "aaa",null,null);

	static {
		recherchesForDTO = new ArrayList<>();
		recherchesForDTO.add(new Recherche("AAA", "A", user));
		recherchesForDTO.add(new Recherche("AAB", "A", user));
		recherchesForDTO.add(new Recherche("ABB", "A", user));
		recherchesForDTO.add(new Recherche("BBB", "B", user));
		recherchesForDTO.add(new Recherche("BBA", "B", user));
		recherchesForDTO.add(new Recherche("CCC", "C", user));
		recherches = recherchesForDTO.stream()
				.map(r -> new AngularRechercheDto(r.getPoste(),r.getStatut())).collect(Collectors.toList());
	}



	@Test
//	@WithMockUser(value = "testjwtclientid")
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
//	@WithMockUser(value = "testjwtclientid")
	public void shouldGetAllRechechesById() throws Exception {

		String result = this.toJsonString(recherches);
		when(rechercheServiceMock.getRecherchesByUser(user)).thenReturn(recherches);
		when(userRepoMock.findOneByEmail(user.getEmail())).thenReturn(user);

		mockMvc.perform(get("/rest/recherches/aaa@ppp.ch"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(result));
	}

	@Test
//	@WithMockUser(value = "testjwtclientid")
	public void shouldUpdateRecherche() throws Exception {
		AngularRechercheDto rech = angularRecherceBuilder();
		rech.setId(1L);
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
//	@WithMockUser(value = "testjwtclientid")
	public void shouldAddRecherche() throws Exception {
		AngularRechercheDto rech = angularRecherceBuilder();
		AngularRechercheDto rechRes = angularRecherceBuilder();
		rechRes.setId(1L);
		String result = this.toJsonString(rechRes);

		when(rechercheServiceMock.addRecherche(rech)).thenReturn(rechRes);

		mockMvc.perform(post("/rest/recherche")
				.content(this.toJsonString(rech))
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(header().string("Location", "http://localhost/rest/recherche/1"))
				.andExpect(content().json(result));
	}

	@Test
//	@WithMockUser(value = "testjwtclientid")
	public void shouldDeleteRecherche() throws Exception {
		AngularRechercheDto rech = angularRecherceBuilder();
		rech.setId(1L);
		Recherche recherche = rechercheBuilder();
		recherche.setId(1L);
		String result = this.toJsonString(rech);

		when(rechercheServiceMock.getRechercheByIdAndUser(any())).thenReturn(rechercheBuilder());
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
		return new Recherche("Web Dev","EN COURS", user);
	}

	private AngularRechercheDto angularRecherceBuilder(){
		return new AngularRechercheDto("Web Dev","EN COURS");
	}
}
