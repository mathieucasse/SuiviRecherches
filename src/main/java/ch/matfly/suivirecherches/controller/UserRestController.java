package ch.matfly.suivirecherches.controller;

import ch.matfly.suivirecherches.dao.UserRepo;
import ch.matfly.suivirecherches.model.User;
import ch.matfly.suivirecherches.model.dto.AngularRechercheDto;
import ch.matfly.suivirecherches.model.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@Slf4j
@RequestMapping("rest")
public class UserRestController {


    @Autowired  UserRepo userRepo;

    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @PostMapping("user")
    @ResponseBody
    @ApiOperation(value = "add a user",
            notes = "add a user from an user",
            response = AngularRechercheDto.class)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        log.debug("========== addRecherche  : " + userDto);
        User user = userRepo.save(userDto.buildNewUser());
        if(null != user.getId()) {
            log.debug("added Recherche : " + user.toString());
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(userDto);
        }else {
            return ResponseEntity.unprocessableEntity().build();
        }

    }
}
