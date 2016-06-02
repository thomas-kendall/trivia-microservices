package trivia.token.service;

import java.security.Key;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import trivia.token.dto.AuthTokenDetailsDTO;

public class TokenService {

	public String createJsonWebToken(AuthTokenDetailsDTO authTokenDetailsDTO) {

		// Calendar calendar = Calendar.getInstance();
		// calendar.add(Calendar.MINUTE,
		// authTokenDetailsDTO.minutesBeforeExpiration);
		// Date expirationDate = calendar.getTime();

		// TODO Can we get a key from a properties file instead?
		Key key = MacProvider.generateKey();
		String token = Jwts.builder().setSubject(authTokenDetailsDTO.userId).claim("email", authTokenDetailsDTO.email)
				.claim("roles", authTokenDetailsDTO.roleNames).setExpiration(authTokenDetailsDTO.expirationDate)
				.signWith(SignatureAlgorithm.HS256, key).compact();
		return token;
	}

	public AuthTokenDetailsDTO parseAndValidate(String token) {
		AuthTokenDetailsDTO authTokenDetailsDTO = null;
		try {
			// TODO use global key
			Key key = MacProvider.generateKey();

			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			String userId = claims.getSubject();
			String email = (String) claims.get("email");
			List<String> roleNames = (List<String>) claims.get("roleNames");
			Date expirationDate = (Date) claims.get("expirationDate");

			authTokenDetailsDTO = new AuthTokenDetailsDTO();
			authTokenDetailsDTO.userId = userId;
			authTokenDetailsDTO.email = email;
			authTokenDetailsDTO.roleNames = roleNames;
			authTokenDetailsDTO.expirationDate = expirationDate;
		} catch (JwtException ex) {
			System.out.println(ex);
		}
		return authTokenDetailsDTO;
	}
}
