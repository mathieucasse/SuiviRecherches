package ch.matfly.suivirecherches.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Recherche_Statut")
public class RechercheStatut {
	
	@Id
	private Integer id;
	private String name;
	private String value;

}
