package ch.matfly.suivirecherches.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;

import lombok.Data;

@Data
@Entity
@Audited
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String rue;
	private String ville;
	private String cp;
	
	@OneToOne(mappedBy = "adresse")
    private Entreprise entreprise;

	public Adresse(String rue, String ville, String cp) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.cp = cp;
	}

	public Adresse() {
		super();
	}

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", rue=" + rue + ", ville=" + ville + ", cp=" + cp + "]";
	}
	
}


