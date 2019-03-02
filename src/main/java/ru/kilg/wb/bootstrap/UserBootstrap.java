package ru.kilg.wb.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.kilg.wb.domain.auth.AuthGroup;
import ru.kilg.wb.domain.auth.User;
import ru.kilg.wb.repositories.auth.AuthGroupRepository;
import ru.kilg.wb.repositories.auth.UserRepository;

@Component
public class UserBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;

    private final AuthGroupRepository authGroupRepository;


    @Autowired
    public UserBootstrap(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        this.authGroupRepository = authGroupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");

        userRepository.save(user);

        AuthGroup authGroup = new AuthGroup();
        authGroup.setAuthGroup("ADMIN");
        authGroup.setUser(user);


        authGroupRepository.save(authGroup);

    }
}
