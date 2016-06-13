package trivia.ui.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import trivia.common.jsonwebtoken.AuthTokenDetailsDTO;
import trivia.common.jsonwebtoken.JsonWebTokenUtility;

@Component
public class JsonWebTokenAuthenticationProvider implements AuthenticationProvider {

	private JsonWebTokenUtility tokenService = new JsonWebTokenUtility();

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication authenticatedUser = null;
		// Only process the PreAuthenticatedAuthenticationToken
		if (authentication.getClass().isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
				&& authentication.getPrincipal() != null) {
			String tokenHeader = (String) authentication.getPrincipal();
			UserDetails userDetails = parseToken(tokenHeader);
			if (userDetails != null) {
				authenticatedUser = new JsonWebTokenAuthentication(userDetails, tokenHeader);
			}
		} else {
			// It is already a JsonWebTokenAuthentication
			authenticatedUser = authentication;
		}
		return authenticatedUser;
	}

	private UserDetails parseToken(String tokenHeader) {

		UserDetails principal = null;
		AuthTokenDetailsDTO authTokenDetails = tokenService.parseAndValidate(tokenHeader);

		if (authTokenDetails != null) {
			List<GrantedAuthority> authorities = authTokenDetails.roleNames.stream()
					.map(roleName -> new SimpleGrantedAuthority(roleName)).collect(Collectors.toList());
			principal = new User(authTokenDetails.email, "", authorities);
		}

		return principal;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
				|| authentication.isAssignableFrom(JsonWebTokenAuthentication.class);
	}

}
