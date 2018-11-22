package lt.bit.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

public class AuthFilter extends AbstractAuthenticationProcessingFilter {

	protected AuthFilter() {
		super(new AuthRequestMatcher());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String header = request.getHeader("Authorization");
		if (StringUtils.isEmpty(header)) {
			throw new AuthenticationCredentialsNotFoundException("no authorization header");
		} else {
			return getAuthenticationManager().authenticate(new AuthDetails(header));
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		SecurityContext sCont = SecurityContextHolder.createEmptyContext();
		sCont.setAuthentication(authResult);
		SecurityContextHolder.setContext(sCont);
		chain.doFilter(request, response);
//		SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
