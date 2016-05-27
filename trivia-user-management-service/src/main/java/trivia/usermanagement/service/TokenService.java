package trivia.usermanagement.service;

import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import trivia.usermanagement.dto.RoleDTO;
import trivia.usermanagement.dto.UserDTO;

public class TokenService {

	public String createJsonWebToken(UserDTO userDTO, List<RoleDTO> roleDTOs) {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		Date expirationDate = calendar.getTime();

		List<String> roleNames = new ArrayList<String>();
		for (RoleDTO roleDTO : roleDTOs) {
			roleNames.add(roleDTO.name);
		}

		Key key = MacProvider.generateKey();
		String token = Jwts.builder().setSubject(userDTO.id).claim("email", userDTO.email).claim("roles", roleNames)
				.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, key).compact();
		return token;
	}
}
