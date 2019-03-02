package ru.kilg.wb.controllers;


import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kilg.wb.domain.EmailMessage;
import ru.kilg.wb.domain.auth.AuthGroup;
import ru.kilg.wb.domain.auth.User;
import ru.kilg.wb.domain.dso.auth.UserCommand;
import ru.kilg.wb.exceptins.RegistrationException;
import ru.kilg.wb.services.EmailService;
import ru.kilg.wb.services.auth.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {


    private UserService userService;
    private EmailService emailService;

    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }


    @GetMapping("/registration")
    public String getRegistration(Model model) {
        UserCommand userCommand = new UserCommand();
        model.addAttribute("usercomm", userCommand);
        return "registration/form";
    }

    @PostMapping("/registration")
    public String postRegistration(@Valid UserCommand userCommand, BindingResult bindingResult, Model model) {
        // thymeleaf store errors in field.?
        if (bindingResult.hasErrors()) {
            model.addAttribute("usercomm", userCommand);
            return "registration/form";
        }


        String confirmCode = RandomString.make();
        User user = userService.saveNewUser(userCommand, confirmCode);

        emailService.sendEmail(new EmailMessage(user, "Confirm Code",
                "You confirm code is <a href=\"localhost:8080/confirm?confirmCode=" + confirmCode + "\">" + confirmCode + "</a>"
        ));


        model.addAttribute("message", "We will send you email with confirm code");
        return "registration/success";
    }

    @ExceptionHandler(value = RegistrationException.class)
    public String userExists(Model model, Exception ex) {
        model.addAttribute("error", ex.getMessage());
        return "registration/error";
    }
}
