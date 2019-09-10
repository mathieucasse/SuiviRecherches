package ch.matfly.suivirecherches.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="of")
@EqualsAndHashCode(of= {"id","contactdate", "poste","client","statut","assignationORP","tauxactivite","approchemedia","entreprise_id","personne_id","rev","revtype", "revtstmp"})
@ToString(of= {"id","contactdate", "poste","client","statut","assignationORP","tauxactivite","approchemedia","entreprise_id","personne_id","rev","revtype", "revtstmp"})
public class RechercheAuditDto {
	
	@Getter @Setter	private Long id;
	@Getter @Setter	private String contactdate;
	@Getter @Setter private String poste;
	@Getter @Setter private String client;
	@Getter @Setter private String statut;
	@Getter @Setter private String assignationORP;
	@Getter @Setter private String tauxactivite;
	@Getter @Setter private String approchemedia;
	@Getter @Setter private Integer entreprise_id;
	@Getter @Setter private Integer personne_id;
	@Getter @Setter  private Integer rev;
	@Getter @Setter  private Integer revtype;
	@Getter @Setter  private String revtstmp;

}
