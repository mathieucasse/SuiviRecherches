package ch.matfly.suivirecherches.service;

import ch.matfly.suivirecherches.dao.EvenementRepo;
import ch.matfly.suivirecherches.model.Evenement;
import ch.matfly.suivirecherches.model.Recherche;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EvenementService {

    @Autowired private EvenementRepo evenementRepo;
    @Autowired private RechercheService rechercheService;
    @Autowired private JdbcTemplate jdbcTemplate;

    public List<Evenement> getEvenementsByRechercheId(Long id){
        Recherche recherche = new Recherche(id);
        return evenementRepo.findAllByRecherche(recherche);
    }

    public Evenement saveEvenement(Evenement evenement) {
        log.debug(">>>>>>>>>  Service ==== saveEvenement  : " + evenement);
        evenement.setRecherche(rechercheService.getRechercheByEvenement(evenement));
        evenement = evenementRepo.save(evenement);
        if(null != evenement.getId()) {
            log.debug("added Evenement : " + evenement.toString());
            return evenement;
        }
        return null;
    }

    public Evenement delEvenement(String id) {
        log.debug("========== Service delEvenement  : " + id);
        Evenement evenement = evenementRepo.findById(Long.valueOf(id)).orElse(null);
        if(null != evenement) {
            log.debug( "==== delEvenement : " + evenement.toString());
            evenementRepo.delete(evenement);
            return evenement;
        }else {
            return null;
        }
    }}
