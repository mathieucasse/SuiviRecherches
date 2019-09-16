package ch.matfly.suivirecherches.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="of")
@EqualsAndHashCode(of= {"id","contactdate", "poste","statut","assignationORP","tauxactivite","approchemedia","entrepriseS_id","personneS_id","entrepriseF_id","personneF_id","rev","revtype", "revtstmp"})
@ToString(of= {"id","contactdate", "poste","statut","assignationORP","tauxactivite","approchemedia","entrepriseS_id","personneS_id","entrepriseF_id","personneF_id","rev","revtype", "revtstmp"})
public class RechercheAuditDto {
	
	@Getter @Setter	private Long id;
	@Getter @Setter	private String contactdate;
	@Getter @Setter private String poste;
	@Getter @Setter private String statut;
	@Getter @Setter private String assignationORP;
	@Getter @Setter private String tauxactivite;
	@Getter @Setter private String approchemedia;
	@Getter @Setter private Long entrepriseS_id;
	@Getter @Setter private Long personneS_id;
	@Getter @Setter private Long entrepriseF_id;
	@Getter @Setter private Long personneF_id;
	@Getter @Setter  private Integer rev;
	@Getter @Setter  private Integer revtype;
	@Getter @Setter  private String revtstmp;

}
