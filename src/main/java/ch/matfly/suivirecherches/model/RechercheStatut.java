package ch.matfly.suivirecherches.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="of")
@EqualsAndHashCode(of= {"id","value", "name"})
@ToString(of= {"id","value","name"})
@Table(name = "Recherche_Statut")
public class RechercheStatut {
	
	@Id
	private Integer id;
	private String name;
	private String value;

}
