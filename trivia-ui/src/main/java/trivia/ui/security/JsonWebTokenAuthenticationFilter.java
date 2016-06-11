package trivia.ui.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JsonWebTokenAuthenticationFilter extends RequestHeaderAuthenticationFilter {

	public JsonWebTokenAuthenticationFilter() {

		// This is the request header it will look for
		this.setPrincipalRequestHeader("Authorization");
		this.setExceptionIfHeaderMissing(false);
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		UserDetails userDetails = null;
		String tokenHeader = (String) super.getPreAuthenticatedPrincipal(request);
		if (tokenHeader != null) {
			userDetails = parseToken(tokenHeader);
			// TODO Do anything on failure or just leave null?
		}

		return userDetails;
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
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
}
