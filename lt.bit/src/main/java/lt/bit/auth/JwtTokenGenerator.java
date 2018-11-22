package lt.bit.auth;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenGenerator {

	@Value("${jwt.secret}")
	private String secret;

	public String generateAccessToken() {

		Long accessTokenExpInMinutes = Long.valueOf(30 * 24 * 60);
		SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

		Claims claims = Jwts.claims()
				.setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
				.setExpiration(Date.from(ZonedDateTime.now()
						.plusMinutes(accessTokenExpInMinutes)
						.toInstant()))
				.setSubject("bananas");

		List<String> scopes = Collections.emptyList();

		claims.put("scopes", scopes);

		String accessToken = Jwts
				.builder()
				.setClaims(claims)
				.signWith(algorithm, secret)
				.compact();

		return accessToken;
	}

}
