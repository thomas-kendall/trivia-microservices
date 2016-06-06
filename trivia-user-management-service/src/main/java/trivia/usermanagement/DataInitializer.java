package trivia.usermanagement;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import trivia.usermanagement.dto.RoleDTO;
import trivia.usermanagement.dto.UserDTO;
import trivia.usermanagement.service.UserManagementService;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private UserManagementService service;

	private Collection<Integer> roleDTOsToIdCollection(RoleDTO... roles) {
		Collection<Integer> idCollection = new ArrayList<>();
		for (RoleDTO role : roles) {
			idCollection.add(role.id);
		}
		return idCollection;
	}

	@Override
	public void run(String... arguments) throws Exception {
		// Create Roles
		RoleDTO adminRole = service.createRole(new RoleDTO("admin"));
		RoleDTO managerRole = service.createRole(new RoleDTO("manager"));

		// Create Users
		UserDTO userA = service.createUser(new UserDTO("a@alpha.org", "password-a", roleDTOsToIdCollection(adminRole)));
		UserDTO userB = service
				.createUser(new UserDTO("b@alpha.org", "password-b", roleDTOsToIdCollection(managerRole)));
		UserDTO userC = service
				.createUser(new UserDTO("c@alpha.org", "password-c", roleDTOsToIdCollection(adminRole, managerRole)));

		service.findAllRoles().forEach(r -> System.out.println(r));
		service.findAllUsers().forEach(u -> System.out.println(u));
	}
}
