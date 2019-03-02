package ru.kilg.wb.services.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kilg.wb.domain.auth.AuthGroup;
import ru.kilg.wb.domain.auth.User;
import ru.kilg.wb.repositories.auth.AuthGroupRepository;
import ru.kilg.wb.repositories.auth.UserRepository;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private AuthGroupRepository authGroupRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        List<AuthGroup> authGroups = authGroupRepository.findByUser(user);

        if (user == null) {
            throw new UsernameNotFoundException("Not found user - " + s);
        }

        return new UserDetailsImpl(user, authGroups);
    }
}
