package com.group3b.project.controllers;

import com.group3b.project.models.User;
import com.group3b.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class SecurityController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/signup")
    public String register(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirm") String confirm,
            Model model) {
        if (email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            model.addAttribute("message", "Please fill in all blanks!");
            return "signup";
        } else if (!password.equals(confirm)) {
            model.addAttribute("message", "Your password doesn't match!");
            return "signup";
        } else if(!email.matches("\\S+@\\S+\\.\\S+")){
            model.addAttribute("message", "Wrong email format!");
            return "signup";
        }

        User result = userRepository.getUser(email);
        if (result != null) {
            model.addAttribute("message", "User already exists");
            return "signup";
        }
        User user = new User(email, password);
        userRepository.addUser(user);

        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {

        if (email.isEmpty() || password.isEmpty()) {
            model.addAttribute("message", "Please fill in all blanks!");
            return "login";
        }else if(!email.matches("\\S+@\\S+\\.\\S+")){
            model.addAttribute("message", "Wrong email format!");
            return "login";
        }

        User result = userRepository.getUser(email);
        if(result == null){
            model.addAttribute("message", "User does not exists");
            return "login";
        }else if(!result.getPassword().equals(password)){
            model.addAttribute("message", "Wrong password");
            return "login";
        }

        model.addAttribute("message", "You logged in(WIP)");
        return "login";
    }
}