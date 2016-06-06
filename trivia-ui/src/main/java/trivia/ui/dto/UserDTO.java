package trivia.ui.dto;

import java.util.List;

public class UserDTO {
	public int id;
	public String email;
	public List<Integer> roleIds;

	public UserDTO() {
	}

	public UserDTO(int id, String email, List<Integer> roleIds) {
		this.id = id;
		this.email = email;
		this.roleIds = roleIds;
	}
}
