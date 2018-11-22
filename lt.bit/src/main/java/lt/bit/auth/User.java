package lt.bit.auth;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username")
	private String username;
	@Column()
	private String password;
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(
			name = "roles",
			joinColumns=@JoinColumn(name = "user_id", referencedColumnName = "id")
			)
	@Column(name="name")
	@Enumerated(EnumType.STRING)
	private Set<Roles> roles = new HashSet<Roles>();
	
	public String getUsername() {
		return username;
	}
	public User setUsername(String username) {
		this.username = username;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	public Set<Roles> getRoles() {
		return roles;
	}
	public User setRoles(Set<Roles> roles) {
		this.roles = roles;
		return this;
	}
	
	
}
