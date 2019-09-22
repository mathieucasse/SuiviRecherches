package ch.matfly.suivirecherches.model.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j
@AllArgsConstructor(staticName="of")
@EqualsAndHashCode(of= {"id","eventDate", "titre","texte","recherche_id","rev","revtype","revtstmp"})
@ToString(of= {"id","eventDate", "titre","texte","recherche_id","rev","revtype","revtstmp"})
public class EvenementAuditDto {

    @Getter @Setter  private Long id;
    @Getter @Setter  private String eventDate;
    @Getter @Setter  private String titre;
    @Getter @Setter  private String texte;
    @Getter @Setter  private Long recherche_id;
    @Getter @Setter  private Integer rev;
    @Getter @Setter  private Integer revtype;
    @Getter @Setter  private String revtstmp;
}
