package trivia.ui.api;

import java.util.Collection;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import trivia.ui.dto.AuthTokenDTO;
import trivia.ui.dto.AuthenticationDTO;
import trivia.ui.dto.RoleDTO;
import trivia.ui.dto.UserDTO;

@FeignClient("trivia-user-management-service")
public interface TriviaUserManagementServiceAPI {

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	AuthTokenDTO authenticateUser(@RequestBody AuthenticationDTO authenticationDTO);

	@RequestMapping(method = RequestMethod.POST, value = "/roles")
	RoleDTO createRole(@RequestHeader("Authorization") String authorizationToken, @RequestBody RoleDTO roleDTO);

	@RequestMapping(method = RequestMethod.POST, value = "/users")
	UserDTO createUser(@RequestHeader("Authorization") String authorizationToken, @RequestBody UserDTO userDTO);

	@RequestMapping(method = RequestMethod.DELETE, value = "/roles/{id}")
	void deleteRole(@RequestHeader("Authorization") String authorizationToken, @PathVariable("id") int id);

	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
	void deleteUser(@RequestHeader("Authorization") String authorizationToken, @PathVariable("id") int id);

	@RequestMapping(method = RequestMethod.GET, value = "/roles")
	Collection<RoleDTO> findAllRoles(@RequestHeader("Authorization") String authorizationToken);

	@RequestMapping(method = RequestMethod.GET, value = "/users")
	Collection<UserDTO> findAllUsers(@RequestHeader("Authorization") String authorizationToken);

	@RequestMapping(method = RequestMethod.GET, value = "/roles/{id}", produces = "application/json", consumes = "application/json")
	RoleDTO findRoleById(@RequestHeader("Authorization") String authorizationToken, @PathVariable("id") int id);

	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}", produces = "application/json", consumes = "application/json")
	UserDTO findUserById(@RequestHeader("Authorization") String authorizationToken, @PathVariable("id") int id);

	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}/roles")
	Collection<RoleDTO> findUserRoles(@RequestHeader("Authorization") String authorizationToken,
			@PathVariable("id") int id);

	@RequestMapping(method = RequestMethod.PUT, value = "/roles/{id}")
	void updateRole(@RequestHeader("Authorization") String authorizationToken, @PathVariable("id") int id,
			@RequestBody RoleDTO roleDTO);

	@RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
	void updateUser(@RequestHeader("Authorization") String authorizationToken, @PathVariable("id") int id,
			@RequestBody UserDTO userDTO);
}
