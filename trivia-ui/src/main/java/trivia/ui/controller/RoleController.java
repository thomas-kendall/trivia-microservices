package trivia.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trivia.ui.api.TriviaUserManagementServiceAPI;
import trivia.ui.dto.RoleDTO;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private TriviaUserManagementServiceAPI userManagementAPI;

	@RequestMapping(method = RequestMethod.POST)
	public RoleDTO createRole(@RequestBody RoleDTO role) {
		return userManagementAPI.createRole(role);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteRole(@PathVariable int id) {
		userManagementAPI.deleteRole(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<RoleDTO> findAllRoles() {
		return userManagementAPI.findAllRoles();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RoleDTO findRoleById(@PathVariable int id) {
		return userManagementAPI.findRoleById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateRole(@PathVariable int id, @RequestBody RoleDTO role) {
		userManagementAPI.updateRole(id, role);
	}
}
