package code.challenge.messages.persistence;

import code.challenge.messages.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
