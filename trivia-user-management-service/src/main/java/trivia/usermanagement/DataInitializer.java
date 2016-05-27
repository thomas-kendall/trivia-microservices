package trivia.usermanagement;

import trivia.usermanagement.model.Role;
import trivia.usermanagement.model.User;
import trivia.usermanagement.model.UserRole;
import trivia.usermanagement.repository.RoleRepository;
import trivia.usermanagement.repository.UserRepository;
import trivia.usermanagement.repository.UserRoleRepository;

public class DataInitializer {

	public static void initialize() {
		RoleRepository roleRepo = new RoleRepository();
		UserRepository userRepo = new UserRepository();
		UserRoleRepository userRoleRepo = new UserRoleRepository();

		// Create Roles
		Role adminRole = new Role("admin");
		adminRole = roleRepo.create(adminRole);

		Role managerRole = new Role("manager");
		managerRole = roleRepo.create(managerRole);

		// Create Users
		User userA = new User("a@alpha.org", "password-a");
		userA = userRepo.create(userA);

		User userB = new User("b@alpha.org", "password-b");
		userB = userRepo.create(userB);

		User userC = new User("c@alpha.org", "password-c");
		userC = userRepo.create(userC);

		// Create UserRoles
		UserRole userRole;

		userRole = new UserRole(userA, adminRole);
		userRole = userRoleRepo.create(userRole);

		userRole = new UserRole(userB, managerRole);
		userRole = userRoleRepo.create(userRole);

		userRole = new UserRole(userC, adminRole);
		userRole = userRoleRepo.create(userRole);
		userRole = new UserRole(userC, managerRole);
		userRole = userRoleRepo.create(userRole);

	}
}
