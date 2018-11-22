package lt.bit.configuration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import static java.util.Arrays.stream;

class AuthRequestMatcher implements RequestMatcher {
	private static final AntPathRequestMatcher[] USER_PATH_MATCHERS =
		{ new AntPathRequestMatcher("/products") };

	@Override
	public boolean matches(HttpServletRequest httpServletRequest) {
		return stream(USER_PATH_MATCHERS).anyMatch(m -> m.matches(httpServletRequest));
	}
}