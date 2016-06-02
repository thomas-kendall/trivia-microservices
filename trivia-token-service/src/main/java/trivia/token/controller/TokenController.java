package trivia.token.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trivia.token.dto.AuthTokenDetailsDTO;
import trivia.token.service.TokenService;

@RestController
@RequestMapping("/tokens")
public class TokenController {

	// TODO: Autowire these services
	private TokenService tokenService = new TokenService();

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestBody AuthTokenDetailsDTO tokenDetails) {

		String token = tokenService.createJsonWebToken(tokenDetails);
		return token;
	}

	@RequestMapping(value = "/{token}", method = RequestMethod.GET)
	public AuthTokenDetailsDTO parseAndValidate(@PathVariable String token) {
		AuthTokenDetailsDTO tokenDetails = tokenService.parseAndValidate(token);
		return tokenDetails;
	}

}
