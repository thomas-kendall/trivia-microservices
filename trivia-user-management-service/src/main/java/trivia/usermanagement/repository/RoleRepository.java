package trivia.usermanagement.repository;

import java.util.ArrayList;
import java.util.List;

import trivia.usermanagement.model.Role;

public class RoleRepository {
	private static List<Role> roles = new ArrayList<Role>();
	private static int nextId = 1;

	private static String getNextId() {
		return Integer.toString(nextId++);
	}

	private static List<Role> getRoles() {
		return roles;
	}

	public Role create(Role role) {
		role.setId(getNextId());
		getRoles().add(role);
		return role;
	}

	public void delete(String id) {
		Role repoRole = findById(id);
		if (repoRole == null) {
			throw new IllegalArgumentException("Role not found for id: " + id);
		}
		getRoles().remove(repoRole);
	}

	public List<Role> findAll() {
		return getRoles();
	}

	public Role findById(String id) {
		Role result = null;
		for (Role role : getRoles()) {
			if (role.getId().equals(id)) {
				result = role;
				break;
			}
		}
		return result;
	}

	public Role update(String id, Role role) {
		Role repoRole = findById(id);
		if (repoRole == null) {
			throw new IllegalArgumentException("Role not found for id: " + id);
		}
		repoRole.setName(role.getName());
		return repoRole;
	}

}
