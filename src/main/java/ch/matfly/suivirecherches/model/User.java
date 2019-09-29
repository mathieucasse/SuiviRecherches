package ch.matfly.suivirecherches.model;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Audited
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="of")
@EqualsAndHashCode(of= {"id","email"})
@ToString(of= {"id","email"})
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Getter @Setter  private String email;

    @OneToMany(mappedBy = "owner")
    @Getter @Setter  private List<Recherche> recherches;

}
