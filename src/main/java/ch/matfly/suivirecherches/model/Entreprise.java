package ch.matfly.suivirecherches.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Audited
public class Entreprise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter  private String nom;
	
	@Getter @Setter  private String telephone;
	
	@OneToOne(cascade = CascadeType.ALL) @JoinColumn( name = "adresse_id" )
	@Setter private Adresse adresse;
	
	@OneToOne(mappedBy = "entreprise")
    private Recherche recherche;

	public Entreprise(String nom, String telephone, Adresse adresse) {
		super();
		this.nom = nom;
		this.telephone = telephone;
		this.adresse = adresse;
	}

	public Entreprise() {
		super();
	}

	@Override
	public String toString() {
		return "Entreprise [id=" + id + ", nom=" + nom + ", telephone=" + telephone + ", adresse=" + adresse + "]";
	}
	
	
	
	
}
