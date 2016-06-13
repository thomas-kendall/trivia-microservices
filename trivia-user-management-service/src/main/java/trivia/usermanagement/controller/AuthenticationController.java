package trivia.usermanagement.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trivia.common.jsonwebtoken.AuthTokenDetailsDTO;
import trivia.common.jsonwebtoken.JsonWebTokenUtility;
import trivia.usermanagement.dto.AuthTokenDTO;
import trivia.usermanagement.dto.AuthenticationDTO;
import trivia.usermanagement.dto.RoleDTO;
import trivia.usermanagement.dto.UserDTO;
import trivia.usermanagement.service.UserManagementService;

@RestController
public class AuthenticationController {

	@Autowired
	private UserManagementService userManagementService;

	private JsonWebTokenUtility tokenService = new JsonWebTokenUtility();

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public AuthTokenDTO authenticate(@RequestBody AuthenticationDTO authenticationDTO) {
		AuthTokenDTO authToken = null;

		// Authenticate the user
		UserDTO userDTO = userManagementService.authenticateUser(authenticationDTO.email, authenticationDTO.password);
		// TODO If authentication fails, return an unauthorized error code

		if (userDTO != null) {

			Collection<RoleDTO> roles = userManagementService.findAllRolesForUser(userDTO.id);
			List<String> roleNames = roles.stream().map(r -> r.name).collect(Collectors.toList());

			// Build the AuthTokenDetailsDTO
			AuthTokenDetailsDTO authTokenDetailsDTO = new AuthTokenDetailsDTO();
			authTokenDetailsDTO.userId = "" + userDTO.id;
			authTokenDetailsDTO.email = userDTO.email;
			authTokenDetailsDTO.roleNames = roleNames;
			authTokenDetailsDTO.expirationDate = buildExpirationDate();

			// Create auth token
			String jwt = tokenService.createJsonWebToken(authTokenDetailsDTO);
			if (jwt != null) {
				authToken = new AuthTokenDTO();
				authToken.token = jwt;
			}
		}

		return authToken;
	}

	private Date buildExpirationDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		Date expirationDate = calendar.getTime();
		return expirationDate;
	}
}
