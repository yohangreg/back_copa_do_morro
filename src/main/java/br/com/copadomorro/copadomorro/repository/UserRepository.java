package br.com.copadomorro.copadomorro.repository;

import br.com.copadomorro.copadomorro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
