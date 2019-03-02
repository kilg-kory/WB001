package ru.kilg.wb.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kilg.wb.domain.auth.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsUserByUsernameOrEmail(String username, String email);



}
