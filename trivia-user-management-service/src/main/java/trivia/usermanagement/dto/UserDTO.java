package trivia.usermanagement.dto;

import java.util.Collection;

public class UserDTO {
	public int id;
	public String email;
	public String password;
	public Collection<Integer> roleIds;

	public UserDTO() {
	}

	public UserDTO(int id, String email, Collection<Integer> roleIds) {
		this.id = id;
		this.email = email;
		this.roleIds = roleIds;
	}

	public UserDTO(String email, String password, Collection<Integer> roleIds) {
		this.email = email;
		this.password = password;
		this.roleIds = roleIds;
	}
}
