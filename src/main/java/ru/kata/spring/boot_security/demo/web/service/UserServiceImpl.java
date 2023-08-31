package ru.kata.spring.boot_security.demo.web.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.web.dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.web.exeptions.UserEmailException;
import ru.kata.spring.boot_security.demo.web.exeptions.UserNotFoundException;
import ru.kata.spring.boot_security.demo.web.model.User;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserDaoImpl userDaoImp;

    public UserServiceImpl(UserDaoImpl userDaoImp) {
        this.userDaoImp = userDaoImp;
    }

    @Transactional
    @Override
    public User add(@Valid User user) throws UserEmailException {
        if(!checkEmail(user)) {
            throw new UserEmailException(String.format("Пользователь с email %s уже существует, укажите другой email",user.getEmail()));
        }
        return userDaoImp.add(user);
    }
    @Transactional
    @Override
    public User update(@Valid User updateUser) throws UserEmailException {
        User userFromDB = userDaoImp.getUser(updateUser.getId());
        if(!checkEmail(updateUser)) {
            throw new UserEmailException(
                    String.format("Пользователь с именем %s уже существует, укажите другое имя",updateUser.getEmail()));
        }
        userFromDB.setUsername(updateUser.getUsername());
        userFromDB.setSurName(updateUser.getSurName());
        userFromDB.setAge(updateUser.getAge());
        userFromDB.setEmail(updateUser.getEmail());
        verificationPassword(updateUser,userFromDB);
        userFromDB.setRoles(updateUser.getRoles());
        return userDaoImp.add(userFromDB);
    }
    @Transactional
    @Override
    public List<User> listUsers() {
        try {
            Iterable<User> users = userDaoImp.listUsers();
            List<User> result = new ArrayList<>();
            users.forEach(result::add);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
    @Transactional
    @Override
    public User getUser(long id) throws UserNotFoundException {
        Optional<User> user = Optional.empty();
        try {
            user = Optional.ofNullable(userDaoImp.getUser(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.orElseThrow(() ->
                new UserNotFoundException(String.format("Пользователь с таки id - %d не найден", id)));
    }
    @Transactional
    @Override
    public void removeUser(long id) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(userDaoImp.getUser(id));
        if (user.isPresent()) {
            userDaoImp.removeUser(user.get());
        } else {
            throw new UserNotFoundException(String.format("Пользователь с таки id - %d не найден", id));
        }
    }

    @Transactional
    @Override
    public User findByEmailLike(String email) {
        Optional<User> user = Optional.empty();
        try {
            user = userDaoImp.findByEmailLike(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.orElseThrow();
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userDaoImp.findByEmailLike(email);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User %s not found",email));
        }
        return user.get();
    }

    private void verificationPassword(User updateUser, User userFromDB) {
        if (updateUser.getPassword().equals(userFromDB.getPassword())) {
            userFromDB.setPassword(userFromDB.getPassword());
            return;
        }
        userFromDB.setPassword(new BCryptPasswordEncoder().encode(updateUser.getPassword()));
    }

    @Transactional
    public boolean checkEmail(User user) {
        Optional<User> userEmail =  userDaoImp.findByEmailLike(user.getEmail());
        if(userEmail.isEmpty() || (userEmail.get().getId().equals(user.getId())
                && userEmail.get().getEmail().equals(user.getEmail()))) {
            return true;
        }
        return false;
    }
    @Override
    public String getEmailAuthentication(Principal principal) throws ParseException, JsonProcessingException {
        String getPrincipalAsJson = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(principal);
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(getPrincipalAsJson);
        JSONObject userAttributes = (JSONObject) jsonObject.get("principal");
        return userAttributes.get("email").toString();
    }


}
