package ch.matfly.suivirecherches.model;

import ch.matfly.suivirecherches.model.dto.AngularRechercheDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Audited
@Data
@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= {"id","contactDate", "poste","statut","assignationORP","tauxActivite","approcheMedia","entrepriseService","personneService","entrepriseFinale","personneFinale"})
@ToString(of= {"id","contactDate", "poste","statut","assignationORP","tauxActivite","approcheMedia","entrepriseService","personneService","entrepriseFinale","personneFinale"})
public class Recherche {
	
	@Transient
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter @Basic private Date contactDate;
	@Getter @Setter private String poste;
	@Getter @Setter private String statut;
	@Getter @Setter private String assignationORP;
	@Getter @Setter private String tauxActivite;
	@Getter @Setter private String approcheMedia;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "entrepriseService_id" )
	@Setter private Entreprise entrepriseService;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "personneService_id" )
	@Setter private Personne personneService;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "entrepriseFinale_id" )
	@Setter private Entreprise entrepriseFinale;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "personneFinale_id" )
	@Setter private Personne personneFinale;
	
	@OneToMany(mappedBy = "recherche", cascade = CascadeType.ALL, orphanRemoval = true)
	@Setter private List<Evenement> evenements = new ArrayList<>();

	public Recherche(Date contactDate, String poste, String statut, String assignationORP, String tauxActivite, String approcheMedia, Entreprise entrepriseService, Personne personneService, Entreprise entrepriseFinale, Personne personneFinale) {
		super();
		this.contactDate = contactDate;
		this.poste = poste;
		this.statut = statut;
		this.assignationORP = assignationORP;
		this.tauxActivite = tauxActivite;
		this.approcheMedia = approcheMedia;
		this.entrepriseService = entrepriseService;
		this.personneService = personneService;
		this.entrepriseFinale = entrepriseFinale;
		this.personneFinale = personneFinale;
	}

	public Recherche( String poste, String statut) {
		super();
		this.contactDate = new Date();
		this.poste = poste;
		this.statut = statut;
	}

	public void updateWith(AngularRechercheDto aRecherche) {
		try {
			this.contactDate = sdf.parse(aRecherche.getDateContact());
		}catch(ParseException e) {
			log.info(e.toString());
			this.contactDate = new Date();
		}
		this.poste = aRecherche.getPoste();
		this.statut = aRecherche.getStatut();
		this.assignationORP = aRecherche.getAssignationORP();
		this.tauxActivite = aRecherche.getTauxActivite().toString();
		this.approcheMedia = aRecherche.getApprocheMedia();
		this.entrepriseService.setNom(aRecherche.getEntrepriseS());
		this.entrepriseService.setTelephone(aRecherche.getEntrepriseTelS());
		this.personneService.setNom(aRecherche.getContactNomS());
		this.personneService.setPrenom(aRecherche.getContactPrenomS());
		this.personneService.setTelephone(aRecherche.getContactTelephoneS());
		this.personneService.setEmail(aRecherche.getContactEmailS());
		this.entrepriseFinale.setNom(aRecherche.getEntrepriseF());
		this.entrepriseFinale.setTelephone(aRecherche.getEntrepriseTelF());
		this.personneFinale.setNom(aRecherche.getContactNomF());
		this.personneFinale.setPrenom(aRecherche.getContactPrenomF());
		this.personneFinale.setTelephone(aRecherche.getContactTelephoneF());
		this.personneFinale.setEmail(aRecherche.getContactEmailF());
	}

	public void addEvenement(Evenement evenement){
		this.evenements.add(evenement);
	}
}
