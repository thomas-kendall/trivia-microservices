package trivia.usermanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trivia.usermanagement.dto.AuthenticationDTO;
import trivia.usermanagement.dto.RoleDTO;
import trivia.usermanagement.dto.UserDTO;
import trivia.usermanagement.service.TokenService;
import trivia.usermanagement.service.UserManagementService;

@RestController
@RequestMapping("/tokens")
public class TokenController {

	// TODO: Autowire these services
	private UserManagementService userManagementService = new UserManagementService();
	private TokenService tokenService = new TokenService();

	@RequestMapping(method = RequestMethod.POST)
	public String authenticate(@RequestBody AuthenticationDTO authenticationDTO) {

		UserDTO userDTO = userManagementService.authenticateUser(authenticationDTO.email, authenticationDTO.password);
		if (userDTO == null) {
			// TODO: return an unauthorized error code
			throw new RuntimeException("403 unauthorized");
		}

		List<RoleDTO> roleDTOs = userManagementService.findAllRolesForUser(userDTO.id);

		String token = tokenService.createJsonWebToken(userDTO, roleDTOs);
		return token;
	}

}
