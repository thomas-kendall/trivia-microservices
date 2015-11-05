package trivia.usermanagement.repository;

import java.util.ArrayList;
import java.util.List;

import trivia.usermanagement.model.Role;
import trivia.usermanagement.model.UserRole;

public class UserRoleRepository {
	private static List<UserRole> userRoles;
	private static int nextId = 1;

	private static String getNextId() {
		return Integer.toString(nextId++);
	}

	private static List<UserRole> getUserRoles() {
		if (userRoles == null) {
			userRoles = new ArrayList<UserRole>(
			// Arrays.asList(new UserRole(getNextId(), "admin"), new
			// UserRole(getNextId()...other fields))
			);
		}
		return userRoles;
	}

	public UserRole create(UserRole userRole) {
		userRole.setId(getNextId());
		getUserRoles().add(userRole);
		return userRole;
	}

	public void delete(String id) {
		UserRole repoUserRole = findById(id);
		if (repoUserRole == null) {
			throw new IllegalArgumentException("UserRole not found for id: " + id);
		}
		getUserRoles().remove(repoUserRole);
	}

	public void deleteForRole(String roleId) {
		List<UserRole> objectsToDelete = findAllForRole(roleId);
		List<String> idsToDelete = new ArrayList<String>();
		for (UserRole userRole : objectsToDelete) {
			idsToDelete.add(userRole.getId());
		}

		for (String id : idsToDelete) {
			delete(id);
		}
	}

	public void deleteForUser(String userId) {
		List<UserRole> objectsToDelete = findAllForUser(userId);
		List<String> idsToDelete = new ArrayList<String>();
		for (UserRole userRole : objectsToDelete) {
			idsToDelete.add(userRole.getId());
		}

		for (String id : idsToDelete) {
			delete(id);
		}
	}

	public List<UserRole> findAll() {
		return getUserRoles();
	}

	public List<UserRole> findAllForRole(String roleId) {
		List<UserRole> results = new ArrayList<UserRole>();
		for (UserRole userRole : getUserRoles()) {
			if (userRole.getRole().getId().equals(roleId)) {
				results.add(userRole);
			}
		}
		return results;
	}

	public List<UserRole> findAllForUser(String userId) {
		List<UserRole> results = new ArrayList<UserRole>();
		for (UserRole userRole : getUserRoles()) {
			if (userRole.getUser().getId().equals(userId)) {
				results.add(userRole);
			}
		}
		return results;
	}

	public List<Role> findAllRolesForUser(String userId) {
		List<Role> roles = new ArrayList<Role>();
		for (UserRole userRole : findAllForUser(userId)) {
			roles.add(userRole.getRole());
		}
		return roles;
	}

	public UserRole findById(String id) {
		UserRole result = null;
		for (UserRole userRole : getUserRoles()) {
			if (userRole.getId().equals(id)) {
				result = userRole;
				break;
			}
		}
		return result;
	}

	public UserRole update(String id, UserRole userRole) {
		UserRole repoUserRole = findById(id);
		if (repoUserRole == null) {
			throw new IllegalArgumentException("UserRole not found for id: " + id);
		}
		repoUserRole.setUser(userRole.getUser());
		repoUserRole.setRole(userRole.getRole());
		return repoUserRole;
	}

}
