package ch.matfly.suivirecherches.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Assignation_ORP")
public class AssignationOrp {
	
	@Id
	private Integer id;
	private String value;
}
