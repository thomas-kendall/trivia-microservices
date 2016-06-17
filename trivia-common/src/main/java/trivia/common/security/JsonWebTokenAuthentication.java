package trivia.common.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class JsonWebTokenAuthentication extends AbstractAuthenticationToken {
	private static final long serialVersionUID = -6855809445272533821L;

	private UserDetails principal;
	private String jsonWebToken;

	public JsonWebTokenAuthentication(UserDetails principal, String jsonWebToken) {
		super(principal.getAuthorities());
		this.principal = principal;
		this.jsonWebToken = jsonWebToken;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	public String getJsonWebToken() {
		return jsonWebToken;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
}
