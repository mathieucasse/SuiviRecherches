package ch.matfly.suivirecherches.model;

import java.time.LocalDate;

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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Basic
	private LocalDate creationDate;
	
	@Getter @Setter private String poste;
	
	@Getter @Setter private String statut;
	
	@OneToOne(cascade = CascadeType.ALL) @JoinColumn( name = "entreprise_id" )
	@Setter private Entreprise entreprise;
	
	@OneToOne(cascade = CascadeType.ALL) @JoinColumn( name = "personne_id" )
	@Setter private Personne personne;
	
	@Override
	public String toString() {
		return "Recherche [id=" + id + ", creationDate=" + creationDate + ", poste=" + poste + ", statut=" + statut
				+ ", entreprise=" + entreprise + ", personne=" + personne + "]";
	}

	public Recherche(String poste, String statut, Entreprise entreprise, Personne personne) {
		super();
		this.creationDate = LocalDate.now();
		this.poste = poste;
		this.statut = statut;
		this.entreprise = entreprise;
		this.personne = personne;
	}

	public Recherche( String poste, String statut) {
		super();
		this.creationDate = LocalDate.now();
		this.poste = poste;
		this.statut = statut;
	}

	public Recherche() {
		super();
	}

}
