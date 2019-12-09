package ch.matfly.suivirecherches.util;

import ch.matfly.suivirecherches.dao.RoleRepo;
import ch.matfly.suivirecherches.dao.UserRepo;
import ch.matfly.suivirecherches.model.Role;
import ch.matfly.suivirecherches.model.User;
import ch.matfly.suivirecherches.service.ServiceStaticLists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AppInitializer implements CommandLineRunner {

	@Autowired
	ServiceStaticLists serviceStaticLists;

	@Autowired
	UserRepo userRepo;

	@Autowired
	RoleRepo roleRepo;

	@Override
	public void run(String... args){
		serviceStaticLists.initLists();
		
		log.info("==================");
		log.info("Test Query");

		Role rstandard = roleRepo.findOneByRoleName(Role.USER);
		Role radmin = roleRepo.findOneByRoleName(Role.ADMIN);

		roleRepo.saveAndFlush(radmin);
		roleRepo.saveAndFlush(rstandard);



		List<Role> lrstandard = new ArrayList<>();
		lrstandard.add(rstandard);
		User lambda = User.of("1","lambda@test.ch", "$2a$10$804o5kw5hkf66CUn5o0IT.0dPpojja0zm9gElWb5p72nfPQafLk16",new ArrayList<>(), lrstandard);
		List<Role> lradmin = new ArrayList<>();
		lradmin.add(rstandard);
		lradmin.add(radmin);
		User admin = User.of("2","admin@test.ch", "$2a$10$Ajs6i6CJddRfCIi3q3iu4.sFnWaxiAxJEhc3df.2V/IzEoF1xRJge",new ArrayList<>(), lradmin);

		userRepo.saveAndFlush(lambda);
		userRepo.saveAndFlush(admin);
		/*
INSERT INTO app_role (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');

-- USER
INSERT INTO user (id, email, password) VALUES (1, 'lambda@test', '$2a$10$804o5kw5hkf66CUn5o0IT.0dPpojja0zm9gElWb5p72nfPQafLk16');
INSERT INTO user (id, email, password) VALUES (2, 'admin@test', '$2a$10$Ajs6i6CJddRfCIi3q3iu4.sFnWaxiAxJEhc3df.2V/IzEoF1xRJge');

INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);
		 */

	}

}
