package ru.kilg.wb.services.auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kilg.wb.domain.auth.AuthGroup;
import ru.kilg.wb.domain.auth.User;
import ru.kilg.wb.domain.converters.UserCommandToUser;
import ru.kilg.wb.domain.dso.auth.UserCommand;
import ru.kilg.wb.exceptins.RegistrationException;
import ru.kilg.wb.repositories.auth.AuthGroupRepository;
import ru.kilg.wb.repositories.auth.UserRepository;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private UserCommandToUser userCommandToUser;
    private AuthGroupRepository authGroupRepository;


    public UserServiceImpl(UserRepository userRepository, UserCommandToUser userCommandToUser,
                           AuthGroupRepository authGroupRepository) {
        this.userRepository = userRepository;
        this.userCommandToUser = userCommandToUser;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    @Transactional
    public User saveNewUser(UserCommand userCommand, String confirmCode) {
        if (!userCommand.getPassword().equals(userCommand.getConfirmPassword())) {
            throw new RegistrationException("Password and Confirm Password not equals");
        }

        if (userRepository.existsUserByUsernameOrEmail(userCommand.getUsername(), userCommand.getEmail())) {
            throw new RegistrationException("User exists");
        }

//        AuthGroup authGroup = authGroupRepository.findByAuthGroup("NOOB");
//        if (authGroup == null) {
//            throw new RuntimeException("Not found noob auth group");
//        }

        User detachedUser = userCommandToUser.convert(userCommand);
        if (detachedUser == null) {
            throw new NullPointerException("User for save in saveNewUser is null");
        }

        AuthGroup authGroup = new AuthGroup();
        authGroup.setUser(detachedUser);
        authGroup.setAuthGroup("NOOB");


        detachedUser.getRoles().add(authGroup);
        detachedUser.setConfirmCode(confirmCode);


        return userRepository.save(detachedUser);
    }
}
