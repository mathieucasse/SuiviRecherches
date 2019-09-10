package ch.matfly.suivirecherches.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@EqualsAndHashCode(of= {"id","rue", "ville","cp","entreprise"})
@ToString(of= {"id","rue", "ville","cp","entreprise"})
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Getter @Setter private String rue;
	@Getter @Setter private String ville;
	@Getter @Setter private String cp;
	
	@OneToOne(mappedBy = "adresse")
	@Setter private Entreprise entreprise;

	public Adresse(String rue, String ville, String cp) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.cp = cp;
	}
}


