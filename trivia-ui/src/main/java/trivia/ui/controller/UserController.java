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
public class UserController extends BaseController {

	@Autowired
	private TriviaUserManagementServiceAPI userManagementAPI;

	@RequestMapping(method = RequestMethod.POST)
	public UserDTO createUser(@RequestBody UserDTO user) {
		return userManagementAPI.createUser(getAuthorizationToken(), user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable int id) {
		userManagementAPI.deleteUser(getAuthorizationToken(), id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<UserDTO> findAllUsers() {
		return userManagementAPI.findAllUsers(getAuthorizationToken());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserDTO findUserById(@PathVariable int id) {
		return userManagementAPI.findUserById(getAuthorizationToken(), id);
	}

	@RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
	public Iterable<RoleDTO> findUserRoles(@PathVariable int id) {
		return userManagementAPI.findUserRoles(getAuthorizationToken(), id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateUser(@PathVariable int id, @RequestBody UserDTO user) {
		userManagementAPI.updateUser(getAuthorizationToken(), id, user);
	}
}
