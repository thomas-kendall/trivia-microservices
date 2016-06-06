package trivia.ui.controller;

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

import trivia.ui.api.TriviaTokenServiceAPI;
import trivia.ui.api.TriviaUserManagementServiceAPI;
import trivia.ui.dto.AuthTokenDetailsDTO;
import trivia.ui.dto.AuthenticationDTO;
import trivia.ui.dto.RoleDTO;
import trivia.ui.dto.UserDTO;

@RestController
public class AuthenticationController {

	@Autowired
	private TriviaUserManagementServiceAPI userManagementServiceAPI;

	@Autowired
	private TriviaTokenServiceAPI tokenServiceAPI;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticate(@RequestBody AuthenticationDTO authenticationDTO) {
		String token = null;

		// Authenticate the user
		UserDTO userDTO = userManagementServiceAPI.authenticateUser(authenticationDTO);
		// TODO If authentication fails, return an unauthorized error code

		if (userDTO != null) {

			Collection<RoleDTO> roles = userManagementServiceAPI.findUserRoles(userDTO.id);
			List<String> roleNames = roles.stream().map(r -> r.name).collect(Collectors.toList());

			// Build the AuthTokenDetailsDTO
			AuthTokenDetailsDTO authTokenDetailsDTO = new AuthTokenDetailsDTO();
			authTokenDetailsDTO.userId = userDTO.id;
			authTokenDetailsDTO.email = userDTO.email;
			authTokenDetailsDTO.roleNames = roleNames;
			authTokenDetailsDTO.expirationDate = buildExpirationDate();

			// Create auth token
			token = tokenServiceAPI.create(authTokenDetailsDTO);
		}

		return token;
	}

	private Date buildExpirationDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		Date expirationDate = calendar.getTime();
		return expirationDate;
	}
}
