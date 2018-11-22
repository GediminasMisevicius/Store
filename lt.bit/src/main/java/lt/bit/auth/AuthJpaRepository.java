package lt.bit.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthJpaRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsernameAndPassword(String username, String password);
	Optional<User> findByUsername(String username);
}
