package trivia.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trivia.ui.api.TriviaUserManagementServiceAPI;
import trivia.ui.dto.AuthTokenDTO;
import trivia.ui.dto.AuthenticationDTO;

@RestController
public class AuthenticationController {

	@Autowired
	private TriviaUserManagementServiceAPI userManagementServiceAPI;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public AuthTokenDTO authenticate(@RequestBody AuthenticationDTO authenticationDTO) {
		// Authenticate the user
		AuthTokenDTO authToken = userManagementServiceAPI.authenticateUser(authenticationDTO);
		// TODO If authentication fails, return an unauthorized error code

		return authToken;
	}
}
