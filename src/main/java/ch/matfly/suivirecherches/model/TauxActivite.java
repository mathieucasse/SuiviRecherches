package ch.matfly.suivirecherches.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Taux_Activite")
public class TauxActivite {
	
	@Id
	private Integer id;
	private String value;
}
