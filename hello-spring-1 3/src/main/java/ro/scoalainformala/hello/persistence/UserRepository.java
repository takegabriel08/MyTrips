package ro.scoalainformala.hello.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.scoalainformala.hello.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
