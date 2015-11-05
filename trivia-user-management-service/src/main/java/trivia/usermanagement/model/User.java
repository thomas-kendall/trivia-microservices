package trivia.usermanagement.model;

import org.springframework.data.annotation.Id;

public class User {

	@Id
	private String id;

	private String email;

	public User() {
	}

	public User(String email) {
		this.email = email;
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

}
