package trivia.usermanagement.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trivia.usermanagement.dto.RoleDTO;
import trivia.usermanagement.dto.UserDTO;
import trivia.usermanagement.service.UserManagementService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserManagementService userManagementService;

	@RequestMapping(method = RequestMethod.POST)
	public UserDTO createUser(@RequestBody UserDTO userDTO) {
		return userManagementService.createUser(userDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable int id) {
		userManagementService.deleteUser(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<UserDTO> findAllUsers() {
		return userManagementService.findAllUsers();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserDTO findUserById(@PathVariable int id) {
		return userManagementService.findUserById(id);
	}

	@RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
	public Collection<RoleDTO> findUserRoles(@PathVariable int id) {
		return userManagementService.findAllRolesForUser(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
		userDTO = userManagementService.updateUser(id, userDTO);
	}

}
