package trivia.usermanagement.dto;

public class RoleDTO {
	public int id;
	public String name;

	public RoleDTO() {
	}

	public RoleDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public RoleDTO(String name) {
		this.name = name;
	}
}
