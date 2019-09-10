package ch.matfly.suivirecherches.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Audited
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="of")
@EqualsAndHashCode(of= {"id","nom", "telephone"})
@ToString(of= {"id","nom", "telephone"})
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
}
