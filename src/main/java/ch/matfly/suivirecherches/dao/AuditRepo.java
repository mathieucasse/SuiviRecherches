package ch.matfly.suivirecherches.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import ch.matfly.suivirecherches.model.dto.EntrepriseAuditDto;
import ch.matfly.suivirecherches.model.dto.PersonneAuditDto;
import ch.matfly.suivirecherches.model.dto.RechercheAuditDto;
import ch.matfly.suivirecherches.util.MatFormat;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AuditRepo {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<EntrepriseAuditDto> getEntrepriseAuditRevForId(Long id){

		return jdbcTemplate.query(
				"SELECT e.id, e.rev, e.revtype, r.revtstmp, e.nom, e.telephone " + 
						"FROM ENTREPRISE_AUD e " + 
						"INNER JOIN REVINFO r ON e.rev = r.rev " + 
						"WHERE e.id= ?",new Object[] {id},
						(rs, rowNum) -> EntrepriseAuditDto.of(rs.getLong(1), rs.getInt(2), rs.getInt(3), MatFormat.format(rs.getLong(4)),
								rs.getString(5), rs.getString(6)));
	}	

	public List<PersonneAuditDto> getPersonneAuditRevForId(Long id){

		return jdbcTemplate.query(
				"SELECT p.id, p.nom, p.prenom, p.telephone, p.email, p.rev, p.revtype, r.revtstmp " + 
						"FROM PERSONNE_AUD p " + 
						"INNER JOIN REVINFO r ON r.rev= p.rev " + 
						"WHERE id=?",new Object[] {id},
						(rs, rowNum) -> PersonneAuditDto.of(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
								rs.getInt(6), rs.getInt(7),	MatFormat.format(rs.getLong(8))));
	}

	public List<RechercheAuditDto> getRechercheAuditRevForId(Long id){
		jdbcTemplate.query("SELECT c.id, c.contact_date, c.poste, c.client, c.statut, c.assignationORP, c.taux_activite, c.approche_media, c.entreprise_id, c.personne_id, c.rev, c.revtype, r.revtstmp " + 
				"FROM RECHERCHE_AUD c " + 
				"INNER JOIN REVINFO r ON r.rev= c.rev " + 
				"WHERE id=?",new Object[] {id},new ResultSetExtractor<List<RechercheAuditDto>>(){  
			@Override  
			public List<RechercheAuditDto> extractData(ResultSet rs) throws SQLException,  
			DataAccessException {  

//				List<RechercheAuditDto> list=new ArrayList<RechercheAuditDto>();  
				while(rs.next()){  
					log.debug("============== getRechercheAuditRevForId =="); 
					log.debug("============== getRechercheAuditRevForId  (1)==" + rs.getLong(1));  
					log.debug("============== getRechercheAuditRevForId  (2)==" + rs.getTimestamp(2));
					log.debug("============== getRechercheAuditRevForId  (2)==" + rs.getDate(2));
				}  
				return null;  
			}  
		});  

		return jdbcTemplate.query(
				"SELECT c.id, c.contact_date, c.poste, c.client, c.statut, c.assignationORP, c.taux_activite, c.approche_media, c.entreprise_id, c.personne_id, c.rev, c.revtype, r.revtstmp " + 
						"FROM RECHERCHE_AUD c " + 
						"INNER JOIN REVINFO r ON r.rev= c.rev " + 
						"WHERE id=?",new Object[] {id},
						(rs, rowNum) -> 
				RechercheAuditDto.of(rs.getLong(1), MatFormat.format(rs.getDate(2)), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10),
						rs.getInt(11), rs.getInt(12), MatFormat.format(rs.getLong(13))));
	}
}