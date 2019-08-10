package ch.matfly.suivirecherches.model;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Historique {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long rechercheId;
	
	@Basic
	private LocalDateTime date;
	private String modification;

	public Historique() {
		super();
	}
	
	public Historique(Long rechercheId, String modification) {
		super();
		this.date = LocalDateTime.now();
		this.rechercheId = rechercheId;
		this.modification = modification;
	}
	
	
	
	

}
