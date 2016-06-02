package trivia.usermanagement.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String email;

	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

	// For simplicity sake, we keep a simple hash code. In the real world, we
	// would do something better.
	private int passwordHash;

	protected User() {
	}

	public User(String email, String password) {
		this.email = email;
		setPassword(password);
	}

	public User(String email, String password, Set<Role> roles) {
		this.email = email;
		setPassword(password);
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public int getPasswordHash() {
		return passwordHash;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPassword(String password) {
		int hc = password.hashCode();
		setPasswordHash(hc);
	}

	public void setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		String rolesString = "";
		if (roles != null) {
			for (Role role : roles) {
				rolesString += role.toString();
			}
		}

		return String.format("User[id=%d, email=%s, roles=%s]", id, email, rolesString);
	}
}
