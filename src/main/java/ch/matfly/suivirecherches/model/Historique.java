package ch.matfly.suivirecherches.model;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Audited
public class Historique {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long rechercheId;
	
	@Basic
	private LocalDateTime date;
	
	@Column(length = 1000)
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
