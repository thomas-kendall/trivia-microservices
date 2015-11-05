package trivia.usermanagement.dto;

import java.util.List;

public class UserDTO {
	public String id;
	public String email;
	public List<String> roleIds;

	public UserDTO() {
	}

	public UserDTO(String id, String email, List<String> roleIds) {
		this.id = id;
		this.email = email;
		this.roleIds = roleIds;
	}
}
