package ch.matfly.suivirecherches.dao;

import ch.matfly.suivirecherches.model.dto.EntrepriseAuditDto;
import ch.matfly.suivirecherches.model.dto.EvenementAuditDto;
import ch.matfly.suivirecherches.model.dto.PersonneAuditDto;
import ch.matfly.suivirecherches.model.dto.RechercheAuditDto;
import ch.matfly.suivirecherches.util.MatFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class AuditRepo {

	@Autowired JdbcTemplate jdbcTemplate;

	public List<EntrepriseAuditDto> getEntrepriseAuditRevForId(Long id){

		return jdbcTemplate.query(
				"SELECT e.id, e.rev, e.revtype, r.revtstmp, e.nom, e.telephone " +
						"FROM ENTREPRISE_AUD e " +
						"INNER JOIN REVINFO r ON e.rev = r.rev " +
						"WHERE e.id= ?",
				new Object[] {id},
				(rs, rowNum) -> EntrepriseAuditDto.of(rs.getLong(1), rs.getInt(2),
						rs.getInt(3), MatFormat.format(rs.getLong(4)),
						rs.getString(5), rs.getString(6)));
	}

	public List<PersonneAuditDto> getPersonneAuditRevForId(Long id){

		return jdbcTemplate.query(
				"SELECT p.id, p.nom, p.prenom, p.telephone, p.email, p.rev, p.revtype, r.revtstmp " +
						"FROM PERSONNE_AUD p " +
						"INNER JOIN REVINFO r ON r.rev= p.rev " +
						"WHERE id=?",
				new Object[] {id},
				(rs, rowNum) -> PersonneAuditDto.of(rs.getLong(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7),
						MatFormat.format(rs.getLong(8))));
	}

	public List<RechercheAuditDto> getRechercheAuditRevForId(Long id){

		return jdbcTemplate.query(
				"SELECT c.id, c.contact_date, c.poste, c.statut, c.assignationORP, " +
                        "c.taux_activite, c.approche_media, c.entreprise_service_id, c.personne_service_id, c.entreprise_finale_id, " +
                        "c.personne_finale_id, c.rev, c.revtype, r.revtstmp " +
						"FROM RECHERCHE_AUD c " +
						"INNER JOIN REVINFO r ON r.rev= c.rev " +
						"WHERE id=?",
				new Object[] {id},
				(rs, rowNum) ->	RechercheAuditDto.of(rs.getLong(1),
						MatFormat.format(rs.getDate(2)), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getLong(8), rs.getLong(9),
						rs.getLong(10), rs.getLong(11), rs.getInt(12),
						rs.getInt(13),MatFormat.format(rs.getLong(14))));
	}

	public List<EvenementAuditDto> getEvenementAuditRevForId(Long id){

		return jdbcTemplate.query(
				"SELECT e.id, e.event_date, e.texte, e.titre, e.recherche_id, e.rev, e.revtype, r.revtstmp " +
						"FROM EVENEMENT_AUD e " +
						"INNER JOIN REVINFO r ON r.rev= e.rev " +
						"WHERE id=?",
				new Object[] {id},
				(rs, rowNum) -> EvenementAuditDto.of(rs.getLong(1),
						MatFormat.format(rs.getDate(2)),
						rs.getString(3), rs.getString(4), rs.getLong(5),
						rs.getInt(6),
						rs.getInt(7),MatFormat.format(rs.getLong(8))));
	}
}