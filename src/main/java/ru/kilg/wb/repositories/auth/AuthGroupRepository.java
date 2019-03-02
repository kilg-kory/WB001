package ru.kilg.wb.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kilg.wb.domain.auth.AuthGroup;
import ru.kilg.wb.domain.auth.User;

import java.util.List;

public interface AuthGroupRepository extends JpaRepository<AuthGroup, Long> {
    List<AuthGroup> findByUser(User user);
    AuthGroup findByAuthGroup(String s);

}
