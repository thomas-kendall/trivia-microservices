package trivia.ui.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JsonWebTokenAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication authenticatedUser = null;
		// TODO why is this check necessary? Why is it hitting this
		// authentication provider after it's been through here already?
		if (authentication.getPrincipal() != null
				&& authentication.getPrincipal().getClass().isAssignableFrom(String.class)) {

			String tokenHeader = (String) authentication.getPrincipal();
			UserDetails userDetails = parseToken(tokenHeader);
			if (userDetails != null) {
				authenticatedUser = new JsonWebTokenAuthentication(userDetails, tokenHeader);
			}
		} else {
			// It is already a UserDetails
			authenticatedUser = authentication;
		}
		return authenticatedUser;
	}

	private UserDetails parseToken(String tokenHeader) {
		// TODO actually read the token info
		String username = "todo@finish.this";
		List<String> roleNames = new ArrayList<>();
		roleNames.add("ROLE_ADMIN");
		roleNames.add("ROLE_TODO");

		List<GrantedAuthority> authorities = roleNames.stream().map(roleName -> new SimpleGrantedAuthority(roleName))
				.collect(Collectors.toList());
		User principal = new User(username, "", authorities);

		return principal;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO add custom authentication class that has just a token string
		// Then, fix the cast in the authenticate method
		return true;
	}

}
