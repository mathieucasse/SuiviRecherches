package ch.matfly.suivirecherches.model;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="of")
@EqualsAndHashCode(of= {"id", "nom", "telephone"})
@ToString(of= {"id", "nom", "telephone"})
public class Entreprise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter  private String type;
	@Getter @Setter  private String nom;
	@Getter @Setter  private String telephone;
	
	@OneToOne(cascade = CascadeType.ALL) @JoinColumn( name = "adresse_id" )
	@Setter private Adresse adresse;
	
	@OneToOne(mappedBy = "entrepriseService")
    private Recherche rechercheService;

	@OneToOne(mappedBy = "entrepriseFinale")
	private Recherche rechercheFinale;

	public Entreprise(String nom, String telephone, Adresse adresse) {
		super();
		this.nom = nom;
		this.telephone = telephone;
		this.adresse = adresse;
	}
}
