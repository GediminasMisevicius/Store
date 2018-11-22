package lt.bit.auth;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import static org.springframework.security.core.userdetails.User.withUsername;

public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	private AuthJpaRepository rep;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		/*
		 * Here we are using dummy data, you need to load user data from database or
		 * other third party application
		 */
		User user = findUserbyUername(username);

		UserBuilder builder = null;
		if (user != null) {
			builder = withUsername(username);
			builder.password(user.getPassword());
			List<String> strings = user
					.getRoles()
					.stream()
					.map(Roles::toString)
					.collect(Collectors.toList());
			String[] strArr = new String[strings.size()];
			strArr = strings.toArray(strArr);
			builder.roles(strArr);
		} else {
			throw new UsernameNotFoundException("User not found.");
		}

		return builder.build();
	}

	private User findUserbyUername(String username) {
		return rep.findByUsername(username).orElseThrow();
	}
}
