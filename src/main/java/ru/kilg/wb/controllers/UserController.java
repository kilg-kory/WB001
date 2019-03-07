package ru.kilg.wb.controllers;


import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kilg.wb.domain.EmailMessage;
import ru.kilg.wb.domain.auth.AuthGroup;
import ru.kilg.wb.domain.auth.User;
import ru.kilg.wb.domain.dso.auth.UserCommand;
import ru.kilg.wb.exceptins.RegistrationException;
import ru.kilg.wb.services.EmailService;
import ru.kilg.wb.services.auth.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {


    private AuthenticationManager authenticationManager;
    private UserService userService;
    private EmailService emailService;
    private RememberMeServices rememberMeServices;
    private UserDetailsService userDetailsService;


    public UserController(AuthenticationManager authenticationManager,
                          UserService userService,
                          EmailService emailService,
                          RememberMeServices rememberMeServices,
                          @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {

        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.emailService = emailService;
        this.rememberMeServices = rememberMeServices;

        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/confirm")
    public String getConfirm(Model model, HttpServletRequest request, @RequestParam(required = false) String code) {
        if (code == null) {
            model.addAttribute("message", "Must be code on email");
            return "registration/confirm";
        }

        //in service
        //get security context for username
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String currentPrincipalName = authentication.getName();

        User currentUser = userService.getUserByUsername(currentPrincipalName);

        if (currentUser == null) {
            throw new RuntimeException("User cant be null here because user must be authorised for this page");
        }

        //in service
        if (code.equals(currentUser.getConfirmCode())) {
            //TODO: to java 11
            currentUser.setConfirmCode(null);
            AuthGroup authGroup = new AuthGroup();
            authGroup.setUser(currentUser);
            authGroup.setAuthGroup("USER");


            //TODO: Change to Logger
            System.err.println("change group");

            List<AuthGroup> groupList = new ArrayList<>();
            groupList.add(authGroup);
            userService.deleteAllRoles(currentUser);
            currentUser.setRoles(groupList);
            userService.saveUser(currentUser);

//            context.getAuthentication().setAuthenticated(false);

            /* ************************************************************************************
            auto relogin for user with new authorises. add role_user and activate it
             **************************************************************************************/
            UserDetails userDetails = userDetailsService.loadUserByUsername(currentUser.getUsername());
            Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            context.setAuthentication(newAuth);
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", context);

//            model.addAttribute("message", "success. please relogin <a href=\"/\">Main</a>");
//            return "registration/success";
            return "redirect:/iam";

        } else {
            model.addAttribute("message", "Not equal code");
            return "registration/confirm";
        }
    }


    @GetMapping("/registration")
    public String getRegistration(Model model) {
        UserCommand userCommand = new UserCommand();
        model.addAttribute("usercomm", userCommand);
        return "registration/form";
    }


    @PostMapping("/registration")
    public String postRegistration(@Valid UserCommand userCommand,
                                   BindingResult bindingResult,
                                   Model model,
                                   HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "remember-me", defaultValue = "false") Boolean rememberMe) {

        // thymeleaf store errors in field.?
        if (bindingResult.hasErrors()) {
            model.addAttribute("usercomm", userCommand);
            return "registration/form";
        }


        String confirmCode = RandomString.make();
        User user = userService.saveNewUser(userCommand, confirmCode);

        emailService.sendEmail(new EmailMessage(user, "Confirm Code",
                "You confirm code is <a href=\"localhost:8080/confirm?code=" + confirmCode + "\">" + confirmCode + "</a>"
        ));


        model.addAttribute("message", "We will send you email with confirm code");

        //in service
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), userCommand.getPassword()));
            context.setAuthentication(authenticate);

            if (rememberMe) {
                System.err.println("remember me activated");
                rememberMeServices.loginSuccess(request, response, authenticate);
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", context);
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return "registration/success";
    }

    @ExceptionHandler(value = RegistrationException.class)
    public String userExists(Model model, Exception ex) {
        model.addAttribute("error", ex.getMessage());
        return "registration/error";
    }
}
