package trivia.token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trivia.token.dto.AuthTokenDTO;
import trivia.token.dto.AuthTokenDetailsDTO;
import trivia.token.service.TokenService;

@RestController
@RequestMapping("/tokens")
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestBody AuthTokenDetailsDTO tokenDetails) {

		String token = tokenService.createJsonWebToken(tokenDetails);
		return token;
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public AuthTokenDetailsDTO parseAndValidate(@RequestBody AuthTokenDTO authToken) {
		AuthTokenDetailsDTO tokenDetails = tokenService.parseAndValidate(authToken.token);
		return tokenDetails;
	}

}
