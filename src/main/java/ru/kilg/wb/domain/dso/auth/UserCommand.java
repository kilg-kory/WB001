package ru.kilg.wb.domain.dso.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kilg.wb.domain.auth.AuthGroup;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCommand {

    private Long Id;

    private List<AuthGroup> roles;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    private String confirmPassword;

}
