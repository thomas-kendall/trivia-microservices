package trivia.ui.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import trivia.common.security.JsonWebTokenAuthentication;

public class BaseController {

	protected String getAuthorizationToken() {
		String token = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getClass().isAssignableFrom(JsonWebTokenAuthentication.class)) {
			JsonWebTokenAuthentication jwtAuthentication = (JsonWebTokenAuthentication) authentication;
			token = jwtAuthentication.getJsonWebToken();
		}
		return token;
	}
}
