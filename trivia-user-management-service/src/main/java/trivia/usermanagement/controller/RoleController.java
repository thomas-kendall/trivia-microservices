package trivia.usermanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trivia.usermanagement.dto.RoleDTO;
import trivia.usermanagement.service.UserManagementService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	private UserManagementService userManagementService = new UserManagementService();

	@RequestMapping(method = RequestMethod.POST)
	public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
		return userManagementService.createRole(roleDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteRole(@PathVariable String id) {
		userManagementService.deleteRole(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<RoleDTO> findAllRoles() {
		return userManagementService.findAllRoles();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RoleDTO findRoleById(@PathVariable String id) {
		return userManagementService.findRoleById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateRole(@PathVariable String id, @RequestBody RoleDTO roleDTO) {
		roleDTO = userManagementService.updateRole(id, roleDTO);
	}

}
