package ru.kata.spring.boot_security.demo.web.dao;


import ru.kata.spring.boot_security.demo.web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User add(User user);
    List<User> listUsers();
    User getUser(long id);
    void removeUser(User user);
    Optional<User> findByNameLike(String userName);
    Optional<User> findByEmailLike(String userName);

}
