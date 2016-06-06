package trivia.token.service;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import trivia.token.dto.AuthTokenDetailsDTO;

@Service
public class TokenService {

	private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
	private static Key securityKeyInstance;

	private static Key getSecurityKey() {
		if (securityKeyInstance == null) {
			securityKeyInstance = MacProvider.generateKey(signatureAlgorithm);
		}
		return securityKeyInstance;
	}

	public String createJsonWebToken(AuthTokenDetailsDTO authTokenDetailsDTO) {
		Key key = getSecurityKey();
		String token = Jwts.builder().setSubject(authTokenDetailsDTO.userId).claim("email", authTokenDetailsDTO.email)
				.claim("roles", authTokenDetailsDTO.roleNames).setExpiration(authTokenDetailsDTO.expirationDate)
				.signWith(signatureAlgorithm, key).compact();
		return token;
	}

	public AuthTokenDetailsDTO parseAndValidate(String token) {
		AuthTokenDetailsDTO authTokenDetailsDTO = null;
		try {
			Key key = getSecurityKey();
			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			String userId = claims.getSubject();
			String email = (String) claims.get("email");
			List<String> roleNames = (List<String>) claims.get("roles");
			Date expirationDate = claims.getExpiration();

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
