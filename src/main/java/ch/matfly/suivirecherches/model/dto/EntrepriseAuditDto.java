package ch.matfly.suivirecherches.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j
@AllArgsConstructor(staticName="of")
@EqualsAndHashCode(of= {"id","nom", "telephone","rev","revtype","revtstmp"})
@ToString(of= {"id","nom", "telephone","rev","revtype","revtstmp"})
public class EntrepriseAuditDto {



	@Getter @Setter private Long id;
	@Getter @Setter  private Integer rev;
	@Getter @Setter  private Integer revtype;
	@Getter @Setter  private String revtstmp;
	@Getter @Setter  private String nom;
	@Getter @Setter  private String telephone;

}