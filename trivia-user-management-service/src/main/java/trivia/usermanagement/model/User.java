package trivia.usermanagement.model;

import org.springframework.data.annotation.Id;

public class User {

	@Id
	private String id;

	private String email;

	// For simplicity sake, we keep a simple hash code. In the real world, we
	// would do something better.
	private int passwordHash;

	public User() {
	}

	public User(String email, String password) {
		this.email = email;
		setPassword(password);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof User)) {
			return false;
		}

		return hashCode() == obj.hashCode();
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public int getPasswordHash() {
		return passwordHash;
	}

	@Override
	public int hashCode() {
		if (getId() != null) {
			return getId().hashCode();
		} else if (getEmail() != null) {
			return getEmail().hashCode();
		}

		return 0;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		int hc = password.hashCode();
		setPasswordHash(hc);
	}

	public void setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
	}

}
