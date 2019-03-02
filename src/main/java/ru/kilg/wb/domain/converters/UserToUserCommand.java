package ru.kilg.wb.domain.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kilg.wb.domain.auth.User;
import ru.kilg.wb.domain.dso.auth.UserCommand;

@Component
public class UserToUserCommand implements Converter<User, UserCommand> {
    @Override
    public UserCommand convert(User user) {
        UserCommand userCommand = new UserCommand();
        userCommand.setId(user.getId());
        userCommand.setUsername(user.getUsername());
        userCommand.setPassword(user.getPassword());
        //TODO: add other fields
        return null;
    }
}
