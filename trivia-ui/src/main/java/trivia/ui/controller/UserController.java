package trivia.ui.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import trivia.ui.dto.RoleDTO;
import trivia.ui.dto.UserDTO;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private String userManagementUrlRoot = "http://localhost:8081";

	@RequestMapping(method = RequestMethod.POST)
	public UserDTO createUser(@RequestBody UserDTO user) {
		RestTemplate restTemplate = new RestTemplate();
		UserDTO responseUser = restTemplate.postForObject(getUserManagementUrlRoot() + "/users", user, UserDTO.class);
		return responseUser;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable String id) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(getUserManagementUrlRoot() + "/users/" + id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public UserDTO[] findAllUsers() {
		RestTemplate restTemplate = new RestTemplate();
		UserDTO[] users = restTemplate.getForObject(getUserManagementUrlRoot() + "/users", UserDTO[].class);
		return users;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserDTO findUserById(@PathVariable String id) {
		RestTemplate restTemplate = new RestTemplate();
		UserDTO user = restTemplate.getForObject(getUserManagementUrlRoot() + "/users/" + id, UserDTO.class);
		return user;
	}

	@RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
	public RoleDTO[] findUserRoles(@PathVariable String id) {
		RestTemplate restTemplate = new RestTemplate();
		RoleDTO[] roles = restTemplate.getForObject(getUserManagementUrlRoot() + "/users/" + id + "/roles",
				RoleDTO[].class);
		return roles;
	}

	public String getUserManagementUrlRoot() {
		return userManagementUrlRoot;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateUser(@PathVariable String id, @RequestBody UserDTO user) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(getUserManagementUrlRoot() + "/users/" + id, user);
	}
}
