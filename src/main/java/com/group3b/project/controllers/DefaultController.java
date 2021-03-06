package com.group3b.project.controllers;

import com.group3b.project.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class DefaultController {
    @GetMapping("/")
    public String index() {
        return "signup";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/signup")
    public String register() {
        return "signup";
    }

    @GetMapping("/add-activity")
    public String activities(@SessionAttribute(name="user", required = false) User user,
                             Model model) {
        if(user == null){
            return "login";
        }
        model.addAttribute("email", user.getEmail());
        return "add-activity";
    }

}
