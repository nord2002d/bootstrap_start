package ru.kata.spring.boot_security.demo.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.web.exeptions.UserEmailException;
import ru.kata.spring.boot_security.demo.web.exeptions.UserNotFoundException;
import ru.kata.spring.boot_security.demo.web.model.User;

import java.security.Principal;
import java.util.List;

public interface UserService extends UserDetailsService  {
    User add(User user) throws UserEmailException;

    List<User> listUsers();

    User getUser(long id) throws UserNotFoundException;

    void removeUser(long id) throws UserNotFoundException;

    User update(User user) throws UserEmailException;

    User findByEmailLike(String userName);
    String getEmailAuthentication(Principal principal) throws ParseException, JsonProcessingException;
}
