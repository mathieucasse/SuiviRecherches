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
@EqualsAndHashCode(of= {"id","nom","prenom", "telephone","email","rev","revtype", "revtstmp"})
@ToString(of= {"id","nom","prenom", "telephone","email","rev","revtype", "revtstmp"})
public class PersonneAuditDto {


	@Getter @Setter  private Long id;
	@Getter @Setter  private String nom;
	@Getter @Setter  private String prenom;
	@Getter @Setter  private String telephone;
	@Getter @Setter  private String email;
	@Getter @Setter  private Integer rev;
	@Getter @Setter  private Integer revtype;
	@Getter @Setter  private String revtstmp;
}
