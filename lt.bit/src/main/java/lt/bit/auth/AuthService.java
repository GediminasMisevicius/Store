package lt.bit.auth;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

	private AuthJpaRepository rep;

	private PasswordEncoder passwordEncoder;

	public AuthService(AuthJpaRepository rep, PasswordEncoder passwordEncoder) {
		this.rep = rep;
		this.passwordEncoder = passwordEncoder;
	}

	public boolean authenticateUser(String username, String password) {

		return false;
	}
	
	public boolean registerUser(String username, String password) {
		if(rep.findByUsername(username).isPresent()) {
			return false;
		} else {
			User user = new User()
					.setUsername(username)
					.setPassword(passwordEncoder.encode(password))
					.setRoles(Set.of(Roles.CUSTOMER));
			rep.save(user);
			return true;
		}
	
	}

}
