package ch.matfly.suivirecherches.model.dto;

import ch.matfly.suivirecherches.model.Role;
import ch.matfly.suivirecherches.model.User;
import ch.matfly.suivirecherches.util.StaticLists;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Slf4j
@AllArgsConstructor(staticName="of")
@EqualsAndHashCode(of= {"id","email","password", "roles"})
@ToString(of={"id","email","password", "roles"})
public class UserDto {
    @Getter @Setter  private String id;
    @Getter @Setter  private String email;
    @Getter @Setter  private String password;
    @Getter @Setter  private List<String> roles;

    public User buildNewUser() {

        List<Role> listRoles = null;
        if (!StaticLists.getRoles().isEmpty()){
            listRoles = StaticLists.getRoles().stream()
                    .filter(r -> this.roles.contains(r.getRoleName()))
                    .collect(Collectors.toList());
    }
        return User.of(id,email,password,null,listRoles);
    }
}

