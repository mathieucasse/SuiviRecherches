package ch.matfly.suivirecherches.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Approche_Media")
public class ApprocheMedia {
	
	@Id
	private Integer id;
	private String value;
}
