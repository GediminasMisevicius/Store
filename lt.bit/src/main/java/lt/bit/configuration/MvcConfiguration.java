package lt.bit.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
	       registry.addViewController("/login").setViewName("login");
	       registry.addViewController("/").setViewName("index");
//	       registry.addViewController("/products").setViewName("products");
	       registry.addViewController("/register").setViewName("register");
	
}
}
