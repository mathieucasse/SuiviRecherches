package ch.matfly.suivirecherches.controller;

import ch.matfly.suivirecherches.model.Evenement;
import ch.matfly.suivirecherches.model.dto.AngularRechercheDto;
import ch.matfly.suivirecherches.service.EvenementService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("rest")
@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
public class EventRestController {

    @Autowired private EvenementService evenementService;

    @GetMapping(value="event/byRechercheId/{id}")
    @ApiOperation(value = "Get All Events of a recherche",	notes = "Return a list of AngularRechercheDTO", response = List.class)
    public List<Evenement> getEventsByRecherche(@PathVariable String id) {
        Long rechercheId = Long.decode(id);
        List<Evenement> es = evenementService.getEvenementsByRechercheId(rechercheId);
        es.forEach(val -> val.setRechercheId(rechercheId));
//        log.debug(es.toString());
        return es;
//        return evenementService.getEvenementsByRechercheId(Long.decode(id));
    }

    @PutMapping("event")
    @ResponseBody
    @ApiOperation(value = "update an event",	notes = "update an event from an Event", response = AngularRechercheDto.class)
    public ResponseEntity<Evenement> updateEvent(@RequestBody Evenement evenement) {
        log.debug(">>>>>>>>>  RestController==== updateEvent Called !!! " + evenement);
        Evenement e = evenementService.saveEvenement(evenement);
        return (null != e) ? ResponseEntity.ok().body(e) : ResponseEntity.notFound().build();
    }

    @PostMapping("event")
    @ResponseBody
    @ApiOperation(value = "add an event to a recherche",	notes = "add an event to recherche", response = Evenement.class)
    public ResponseEntity<Evenement> addEvent(@RequestBody Evenement evenement) {

        log.debug(">>>>>>>>>  RestController==== addRechercheEvent Called !!! " + evenement);
        evenement = evenementService.saveEvenement(evenement);
        if(null!=evenement) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(evenement.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(evenement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("event/{id}")
    @ApiOperation(value = "delete  an event",	notes = "delete an event from an id", response = AngularRechercheDto.class)
    public ResponseEntity<Evenement> delEvenement(@PathVariable String id) {
        log.debug("========== delEvenement  : " + id);
        Evenement evenement = evenementService.delEvenement(id);
        if(null != evenement) {
            log.debug( "==== delEvenement : " + evenement.toString());
            return ResponseEntity.accepted().body(evenement);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
