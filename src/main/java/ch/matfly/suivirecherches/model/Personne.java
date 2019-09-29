package ch.matfly.suivirecherches.model;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="of")
@EqualsAndHashCode(of= {"id","nom", "telephone","prenom","email"})
@ToString(of= {"id","nom", "telephone","email"})
@Table(name = "Personne")
public class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter  private String nom;
	@Getter @Setter  private String prenom;
	@Getter @Setter  private String telephone;
	@Getter @Setter  private String email;
	
	@OneToOne(mappedBy = "personneService")
    private Recherche rechercheServ;

	@OneToOne(mappedBy = "personneFinale")
	private Recherche rechercheFin;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User post;

	public Personne(String nom, String prenom, String telephone,String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
	}
}
