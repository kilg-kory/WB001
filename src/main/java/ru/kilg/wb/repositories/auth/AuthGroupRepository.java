package ru.kilg.wb.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kilg.wb.domain.auth.AuthGroup;

import java.util.List;

public interface AuthGroupRepository extends JpaRepository<AuthGroup, Long> {
    List<AuthGroup> findByUsername(String username);
}
