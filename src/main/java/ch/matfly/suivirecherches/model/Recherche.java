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

import ch.matfly.suivirecherches.util.AngularRecherche;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Audited
@Data
@Entity
public class Recherche {
	
	@Transient
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	private Date contactDate;

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

	public Recherche() {
		super();
	}

	@Override
	public String toString() {
		return "Recherche [id=" + id + ", contactDate=" + contactDate + ", poste=" + poste + ", client=" + client
				+ ", statut=" + statut + ", assignationORP=" + assignationORP + ", tauxActivite=" + tauxActivite
				+ ", approcheMedia=" + approcheMedia + ", entreprise=" + entreprise + ", personne=" + personne + "]";
	}

	public Recherche(Long id, Date contactDate, String poste, String client, String statut, String assignationORP,
			String tauxActivite, String approcheMedia, Entreprise entreprise, Personne personne) {
		super();
		this.id = id;
		this.contactDate = contactDate;
		this.poste = poste;
		this.client = client;
		this.statut = statut;
		this.assignationORP = assignationORP;
		this.tauxActivite = tauxActivite;
		this.approcheMedia = approcheMedia;
		this.entreprise = entreprise;
		this.personne = personne;
	}

	public void updateWith(AngularRecherche aRecherche) {
		try {
			this.contactDate = sdf.parse(aRecherche.getDateContact());
		}catch(ParseException e) {
			e.printStackTrace();
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
