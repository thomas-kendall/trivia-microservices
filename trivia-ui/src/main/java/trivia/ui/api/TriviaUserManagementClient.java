package trivia.ui.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import trivia.ui.dto.RoleDTO;
import trivia.ui.dto.UserDTO;

@FeignClient("trivia-user-management-service")
public interface TriviaUserManagementClient {

	@RequestMapping(method = RequestMethod.POST, value = "/roles")
	RoleDTO createRole(@RequestBody RoleDTO roleDTO);

	@RequestMapping(method = RequestMethod.POST, value = "/users")
	UserDTO createUser(@RequestBody UserDTO userDTO);

	@RequestMapping(method = RequestMethod.DELETE, value = "/roles/{id}")
	void deleteRole(@PathVariable("id") String id);

	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
	void deleteUser(@PathVariable("id") String id);

	@RequestMapping(method = RequestMethod.GET, value = "/roles")
	Iterable<RoleDTO> findAllRoles();

	@RequestMapping(method = RequestMethod.GET, value = "/users")
	Iterable<UserDTO> findAllUsers();

	@RequestMapping(method = RequestMethod.GET, value = "/roles/{id}", produces = "application/json", consumes = "application/json")
	RoleDTO findRoleById(@PathVariable("id") String id);

	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}", produces = "application/json", consumes = "application/json")
	UserDTO findUserById(@PathVariable("id") String id);

	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}/roles")
	Iterable<RoleDTO> findUserRoles(@PathVariable("id") String id);

	@RequestMapping(method = RequestMethod.PUT, value = "/roles/{id}")
	void updateRole(@PathVariable("id") String id, @RequestBody RoleDTO roleDTO);

	@RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
	void updateUser(@PathVariable("id") String id, @RequestBody UserDTO userDTO);

}
