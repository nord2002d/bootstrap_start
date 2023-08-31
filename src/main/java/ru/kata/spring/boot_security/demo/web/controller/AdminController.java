package ru.kata.spring.boot_security.demo.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.web.exeptions.UserEmailException;
import ru.kata.spring.boot_security.demo.web.exeptions.UserNotFoundException;
import ru.kata.spring.boot_security.demo.web.model.User;
import ru.kata.spring.boot_security.demo.web.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/")
public class AdminController {
    private UserService userService;
    private static final String REDIRECT = "redirect:/admin/users";

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(value = "user")
    public User getUser()
    {
        return new User();
    }
    @GetMapping
    public String startPage(){
        return "login";
    }
    @GetMapping("/admin")
    public String adminPage( Principal principal, ModelMap model) throws ParseException, JsonProcessingException {
        String email = userService.getEmailAuthentication(principal);
        User user = userService.findByEmailLike(email);
        model.addAttribute("adminUser",user);
        model.addAttribute("admin",userService.listUsers());
        return "admin";
    }

    @GetMapping("/formAdd")
    public String getFormAddUser(@ModelAttribute("user") User user) {

        return "add";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) throws UserEmailException {
        if (bindingResult.hasErrors()) {
            return "add";
        }
        userService.add(user);
        return REDIRECT;
    }

    @DeleteMapping
    public String removeUser(@RequestParam("id") long id) throws UserNotFoundException {
        userService.removeUser(id);
        return REDIRECT;
    }

    @GetMapping("/formUpdate")
    public String getFormUpdate(ModelMap model, @RequestParam("id") long id) throws UserNotFoundException {
        model.addAttribute("userUpdate", userService.getUser(id));
        return "admin";
    }

    @PatchMapping
    public String updateUsers( @ModelAttribute("user") User user,ModelMap model) throws UserEmailException {

//        User user1 = new User();
//        user1.setId(user.getId());
//        userService.update(user);
        return REDIRECT;
    }

}
