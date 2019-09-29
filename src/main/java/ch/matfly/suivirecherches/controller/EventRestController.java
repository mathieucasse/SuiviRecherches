package ch.matfly.suivirecherches.controller;

import ch.matfly.suivirecherches.model.Evenement;
import ch.matfly.suivirecherches.model.dto.AngularRechercheDto;
import ch.matfly.suivirecherches.service.EvenementService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = {
        "http://ec2-3-19-239-63.us-east-2.compute.amazonaws.com",
        "http://ec2-3-19-239-63.us-east-2.compute.amazonaws.com:4200",
        "http://localhost",
        "http://localhost:4200",
        "http://localhost:4202"}, maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("rest")
public class EventRestController {

    @Autowired private EvenementService evenementService;

    @GetMapping(value="event/byRechercheId/{id}")
//    @ResponseBody
    @ApiOperation(value = "Get All Events of a recherche",	notes = "Return a list of AngularRechercheDTO", response = List.class)
    public List<Evenement> getEventsByRecherche(@PathVariable String id) {
        Long rechercheId = Long.decode(id);
        List<Evenement> es = evenementService.getEvenementsByRechercheId(rechercheId);
        es.forEach(val -> val.setRechercheId(rechercheId));
        log.debug(es.toString());
        return es;
//        return evenementService.getEvenementsByRechercheId(Long.decode(id));
    }

    @PutMapping("event")
    @ResponseBody
    @ApiOperation(value = "update an event",	notes = "update an event from an Event", response = AngularRechercheDto.class)
    public ResponseEntity<Evenement> updateEvent(@RequestBody Evenement evenement) {

        log.debug(">>>>>>>>>  RestController==== updateEvent Called !!! " + evenement);
        evenement = evenementService.saveEvenement(evenement);
        if(null!=evenement) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(evenement.getId())
                    .toUri();
            return ResponseEntity.ok().body(evenement);
        } else {
            return ResponseEntity.notFound().build();
        }
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
