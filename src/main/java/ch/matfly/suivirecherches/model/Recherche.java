package ch.matfly.suivirecherches.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import ch.matfly.suivirecherches.model.dto.AngularRechercheDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Audited
@Data
@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= {"id","contactDate", "poste","client","statut","assignationORP","tauxActivite","approcheMedia","entreprise","personne"})
@ToString(of= {"id","contactDate", "poste","client","statut","assignationORP","tauxActivite","approcheMedia","entreprise","personne"})
public class Recherche {
	
	@Transient
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic private Date contactDate;
	@Getter @Setter private String poste;
	@Getter @Setter private String client;
	@Getter @Setter private String statut;
	@Getter @Setter private String assignationORP;
	@Getter @Setter private String tauxActivite;
	@Getter @Setter private String approcheMedia;

	@OneToOne(cascade = CascadeType.ALL) @JoinColumn( name = "entreprise_id" )
	@Setter private Entreprise entreprise;

	@OneToOne(cascade = CascadeType.ALL) @JoinColumn( name = "personne_id" )
	@Setter private Personne personne;


	public Recherche(Date contactDate, String poste, String client, String statut, Entreprise entreprise, Personne personne) {
		super();
		this.contactDate = contactDate;
		this.client = client;
		this.poste = poste;
		this.statut = statut;
		this.entreprise = entreprise;
		this.personne = personne;
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
		this.client = aRecherche.getClient();
		this.statut = aRecherche.getStatut();
		this.assignationORP = aRecherche.getAssignationORP();
		this.tauxActivite = aRecherche.getTauxActivite().toString();
		this.approcheMedia = aRecherche.getApprocheMedia();
		this.entreprise.setNom(aRecherche.getEntreprise());
		this.personne.setNom(aRecherche.getContactNom());
		this.personne.setPrenom(aRecherche.getContactPrenom());
		this.personne.setTelephone(aRecherche.getContactTelephone());
		this.personne.setEmail(aRecherche.getContactEmail());
	}

}
