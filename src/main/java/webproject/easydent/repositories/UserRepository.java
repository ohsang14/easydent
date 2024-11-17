package webproject.easydent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webproject.easydent.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
