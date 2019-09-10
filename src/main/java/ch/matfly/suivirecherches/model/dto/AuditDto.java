package ch.matfly.suivirecherches.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data

public class AuditDto {
	
	@Getter @Setter	List<EntrepriseAuditDto> entrepriseAuditDto = new ArrayList<EntrepriseAuditDto>();
	@Getter @Setter	List<PersonneAuditDto> personneAuditDto = new ArrayList<PersonneAuditDto>();
	@Getter @Setter	List<RechercheAuditDto> rechercheAuditDto = new ArrayList<RechercheAuditDto>();

}
