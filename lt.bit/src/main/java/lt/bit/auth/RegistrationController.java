package lt.bit.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

	private AuthService service;
	
	public RegistrationController(AuthService service) {
		this.service = service;
	}
	
	@PostMapping("/register")
	public String registerUser(HttpServletRequest request) {
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		service.registerUser(user, pass);
		return "login";
	}
	
	@GetMapping("/register")
	public String getTheRegister() {
		return "register";
	}
	
}
