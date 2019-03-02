package ru.kilg.wb.domain.dso.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kilg.wb.domain.auth.AuthGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCommand {

    private Long id;

    private List<AuthGroup> roles;

    @NotBlank
    @Size(min=2, max=30)
    private String name;

    @NotBlank
    @Email
    private String email;


    //like login
    //can change to email? by setters or some
    @NotBlank
    @Size(min=4, max=30)
    private String username;

    @NotBlank
    @Size(min=2, max=30)
    private String password;

    //TODO: Try write own Validator
    private String confirmPassword;

    // Another variant
//    @AssertTrue(message="passVerify field should be equal than pass field")
//    private boolean isValid() {
//        return this.pass.equals(this.passVerify);
//    }

}
