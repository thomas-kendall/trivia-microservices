package trivia.usermanagement.model;

import org.springframework.data.annotation.Id;

public class UserRole {
	@Id
	private String id;

	private User user;
	private Role role;

	public UserRole() {

	}

	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	public User getUser() {
		return user;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
