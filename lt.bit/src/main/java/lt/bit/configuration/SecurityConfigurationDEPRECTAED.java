//package lt.bit.configuration;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import lt.bit.auth.UserDetailsServiceImp;
//
////@Configuration
//@EnableWebSecurity
//public class SecurityConfigurationDEPRECTAED extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	private DataSource dataSource;
//
//	 @Override
//	   protected void configure(HttpSecurity http) throws Exception {
//	       http
//	           .authorizeRequests()
//	               .antMatchers("/", "/register").permitAll()
//	               .anyRequest().authenticated()
//	               .and()
//	           .formLogin()
//	               .loginPage("/login")
//	               .permitAll()
//	               .and()
//	           .logout()
//	               .permitAll();
//	   }
//
//	 @Bean
//	 @Override
//	 public UserDetailsService userDetailsService() {
////		 String query = "select name from roles " +
////		   			"inner join user on roles.user_id = user.id " +
////		   			"where user.username = ?";
//////       UserDetails user =
//////            User.username("user2")
//////               .password("password2")
//////               .roles("USER").withDefaultPasswordEncoder()
//////               
//////	               .build();
////       JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
////       userDetailsService.setDataSource(dataSource);
////       userDetailsService.setAuthoritiesByUsernameQuery(query);
////       
//////       PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//  
//     return new UserDetailsServiceImp();
//	   }
//	 
//	 @Bean
//	 public PasswordEncoder passwordEncoder() {
//	     return new BCryptPasswordEncoder();
//	 }
//	
//}
