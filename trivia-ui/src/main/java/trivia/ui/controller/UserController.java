package trivia.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trivia.ui.api.TriviaUserManagementServiceAPI;
import trivia.ui.dto.RoleDTO;
import trivia.ui.dto.UserDTO;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private TriviaUserManagementServiceAPI userManagementClient;

	@RequestMapping(method = RequestMethod.POST)
	public UserDTO createUser(@RequestBody UserDTO user) {
		return userManagementClient.createUser(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable String id) {
		userManagementClient.deleteUser(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<UserDTO> findAllUsers() {
		return userManagementClient.findAllUsers();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserDTO findUserById(@PathVariable String id) {
		return userManagementClient.findUserById(id);
	}

	@RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
	public Iterable<RoleDTO> findUserRoles(@PathVariable String id) {
		return userManagementClient.findUserRoles(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateUser(@PathVariable String id, @RequestBody UserDTO user) {
		userManagementClient.updateUser(id, user);
	}
}
