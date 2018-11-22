package lt.bit.configuration;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class AuthDetails extends AbstractAuthenticationToken {

	private String authHeader;

	public AuthDetails(String authHeader) {
		super(null);
		this.authHeader = authHeader;
	}

	@Override
	public Object getCredentials() {
		return authHeader;
	}

	@Override
	public Object getPrincipal() {
		//user id, roles, etc. (user object)
		return null;
	}

}
