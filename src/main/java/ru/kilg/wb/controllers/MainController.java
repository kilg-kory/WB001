package ru.kilg.wb.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kilg.wb.domain.dso.auth.UserCommand;

import java.security.Principal;

@Controller
public class MainController {

    @RequestMapping("/iam")
    public String getUserPage(){
        return "user";
    }

    @RequestMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @RequestMapping("/logout-success")
    public String getLogoutPage(Model model) {
        return "logout";
    }

    @RequestMapping({"/", "", "/index", "/index.*"})
    public String getIndexPage(Model model) {

        model.addAttribute("title", "");
        model.addAttribute("usercomm", new UserCommand());
        return "index";
    }


}
