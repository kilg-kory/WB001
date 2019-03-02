package ru.kilg.wb.domain.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.kilg.wb.domain.auth.AuthGroup;
import ru.kilg.wb.domain.auth.User;
import ru.kilg.wb.domain.dso.auth.UserCommand;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserCommandToUser implements Converter<UserCommand, User> {


    @Nullable
    @Synchronized
    @Override
    public User convert(UserCommand source) {

        if (source == null) {
            return null;
        }

        final User user = new User();
        user.setId(source.getId());
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());
        user.setEmail(source.getEmail());
        List<AuthGroup> roles = source.getRoles();
        if (roles == null) {
            roles = new ArrayList<>();
        }
        user.setRoles(roles);
        //TODO: other user fields
        return user;
    }
}
