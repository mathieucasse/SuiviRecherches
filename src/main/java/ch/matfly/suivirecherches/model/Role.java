package ch.matfly.suivirecherches.model;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="of")
@EqualsAndHashCode(of= {"id","roleName"})
@ToString(of= {"id","roleName"})
@Table(name="Role")
public class Role {
    private static final long serialVersionUID = 1L;
    public static final String ADMIN = "ADMIN_USER";
    public static final String USER = "STANDARD_USER";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    private String roleName;

    @Column(name="description")
    private String description;

}
