package ru.kata.spring.boot_security.demo.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.web.model.User;
import ru.kata.spring.boot_security.demo.web.service.UserService;

import java.security.Principal;


@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userDate(Principal principal, ModelMap model) throws ParseException, JsonProcessingException {
        String email = userService.getEmailAuthentication(principal);
        User user = userService.findByEmailLike(email);
        model.addAttribute("user",user);
        return "user";
    }

}