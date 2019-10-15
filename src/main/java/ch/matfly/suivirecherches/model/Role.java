package ch.matfly.suivirecherches.model;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= {"id","roleName"})
@ToString(of= {"id","roleName"})
@Table(name="Role")
public class Role {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    private String roleName;

    @Column(name="description")
    private String description;

}
