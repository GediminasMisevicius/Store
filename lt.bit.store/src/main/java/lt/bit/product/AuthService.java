package lt.bit.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lt.bit.configuration.AuthDetails;

public class AuthService implements AuthenticationProvider {

	@Value("${jwt.secret}")
	private String secret;

	public boolean isAuthenticated(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		} catch (MalformedJwtException | SignatureException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (isAuthenticated((String) authentication.getCredentials())) {
			return authentication;
		} else {
			throw new AuthenticationCredentialsNotFoundException("no credentials found... again");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return AuthDetails.class.isAssignableFrom(authentication);
	}

}
