package ru.kilg.wb.services.auth;

import ru.kilg.wb.domain.auth.User;
import ru.kilg.wb.domain.dso.auth.UserCommand;


public interface UserService {
    User saveNewUser(UserCommand userCommand, String confirmCode);

    User getUserByUsername(String name);

    void saveUser(User currentUser);

    void deleteAllRoles(User user);
}
