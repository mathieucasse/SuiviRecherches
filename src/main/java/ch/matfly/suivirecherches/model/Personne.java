package ch.matfly.suivirecherches.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
@Audited
public class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter  private String nom;
	
	@Getter @Setter  private String prenom;
	
	@Getter @Setter  private String telephone;
	
	@Getter @Setter  private String email;
	
	@OneToOne(mappedBy = "personne")
    private Recherche recherche;

	public Personne(String nom, String prenom, String telephone,String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
	}

	public Personne() {
		super();
	}

	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", email="
				+ email + "]";
	}

	
}
