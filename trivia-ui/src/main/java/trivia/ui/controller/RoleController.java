package trivia.ui.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import trivia.ui.dto.RoleDTO;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	private String userManagementUrlRoot = "http://localhost:8081";

	@RequestMapping(method = RequestMethod.POST)
	public RoleDTO createRole(@RequestBody RoleDTO role) {
		RestTemplate restTemplate = new RestTemplate();
		RoleDTO responseRole = restTemplate.postForObject(getUserManagementUrlRoot() + "/roles", role, RoleDTO.class);
		return responseRole;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteRole(@PathVariable String id) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(getUserManagementUrlRoot() + "/roles/" + id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public RoleDTO[] findAllRoles() {
		RestTemplate restTemplate = new RestTemplate();
		RoleDTO[] roles = restTemplate.getForObject(getUserManagementUrlRoot() + "/roles", RoleDTO[].class);
		return roles;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RoleDTO findRoleById(@PathVariable String id) {
		RestTemplate restTemplate = new RestTemplate();
		RoleDTO role = restTemplate.getForObject(getUserManagementUrlRoot() + "/roles/" + id, RoleDTO.class);
		return role;
	}

	public String getUserManagementUrlRoot() {
		return userManagementUrlRoot;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateRole(@PathVariable String id, @RequestBody RoleDTO role) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(getUserManagementUrlRoot() + "/roles/" + id, role);
	}
}
