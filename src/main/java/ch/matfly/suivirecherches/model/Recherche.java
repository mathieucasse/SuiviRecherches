package ch.matfly.suivirecherches.model;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Recherche {
	
//	static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Basic
	private Date contactDate;
	
	@Getter @Setter private String poste;
	
	@Getter @Setter private String client;
	
	@Getter @Setter private String statut;
	
	@OneToOne(cascade = CascadeType.ALL) @JoinColumn( name = "entreprise_id" )
	@Setter private Entreprise entreprise;
	
	@OneToOne(cascade = CascadeType.ALL) @JoinColumn( name = "personne_id" )
	@Setter private Personne personne;
	
//	public String getFormattedDate() {
//		return sdf.format(this.contactDate);
//	}
	
	@Override
	public String toString() {
		return "Recherche [id=" + id + ", contactDate=" + contactDate + ", client=" + client + ", poste=" + poste + ", statut=" + statut
				+ ", entreprise=" + entreprise + ", personne=" + personne + "]";
	}

	public Recherche(Date conatctDate, String poste, String client, String statut, Entreprise entreprise, Personne personne) {
		super();
		this.contactDate = conatctDate;
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

}
