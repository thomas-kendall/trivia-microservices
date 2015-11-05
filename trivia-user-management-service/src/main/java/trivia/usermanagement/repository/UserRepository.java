package trivia.usermanagement.repository;

import java.util.ArrayList;
import java.util.List;

import trivia.usermanagement.model.User;

public class UserRepository {
	private static List<User> users = new ArrayList<User>();
	private static int nextId = 1;

	private static String getNextId() {
		return Integer.toString(nextId++);
	}

	private static List<User> getUsers() {
		return users;
	}

	public User create(User user) {
		user.setId(getNextId());
		getUsers().add(user);
		return user;
	}

	public void delete(String id) {
		User repoUser = findById(id);
		if (repoUser == null) {
			throw new IllegalArgumentException("User not found for id: " + id);
		}
		getUsers().remove(repoUser);
	}

	public List<User> findAll() {
		return getUsers();
	}

	public User findById(String id) {
		User result = null;
		for (User user : getUsers()) {
			if (user.getId().equals(id)) {
				result = user;
				break;
			}
		}
		return result;
	}

	public User update(String id, User user) {
		User repoUser = findById(id);
		if (repoUser == null) {
			throw new IllegalArgumentException("User not found for id: " + id);
		}
		repoUser.setEmail(user.getEmail());
		// TODO: copy over the roles
		return repoUser;
	}
}
