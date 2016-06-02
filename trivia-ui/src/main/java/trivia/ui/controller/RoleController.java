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
	private TriviaUserManagementServiceAPI userManagementClient;

	@RequestMapping(method = RequestMethod.POST)
	public RoleDTO createRole(@RequestBody RoleDTO role) {
		return userManagementClient.createRole(role);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteRole(@PathVariable String id) {
		userManagementClient.deleteRole(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<RoleDTO> findAllRoles() {
		return userManagementClient.findAllRoles();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RoleDTO findRoleById(@PathVariable String id) {
		return userManagementClient.findRoleById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateRole(@PathVariable String id, @RequestBody RoleDTO role) {
		userManagementClient.updateRole(id, role);
	}
}
