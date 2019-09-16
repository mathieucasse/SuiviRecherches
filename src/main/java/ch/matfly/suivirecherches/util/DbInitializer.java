package ch.matfly.suivirecherches.util;

import ch.matfly.suivirecherches.dao.AuditRepo;
import ch.matfly.suivirecherches.service.ServiceStaticLists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DbInitializer implements CommandLineRunner {
	
	@Autowired
	ServiceStaticLists serviceStaticLists;
	
	@Autowired
	AuditRepo auditRepo;

	@Override
	public void run(String... args) throws Exception {
		serviceStaticLists.initLists();
		
		log.info("==================");
		log.info("Test Query");
//		auditRepo.getEntrepriseAuditRevForId(1l).stream().map(p -> p.toString());
//		log.info(auditRepo.getEntrepriseAuditRevForId(1l).toString());
//		log.info(auditRepo.getPersonneAuditRevForId(1l).toString());
//		log.info(auditRepo.getRechercheAuditRevForId(1l).toString());
		

	}

}
