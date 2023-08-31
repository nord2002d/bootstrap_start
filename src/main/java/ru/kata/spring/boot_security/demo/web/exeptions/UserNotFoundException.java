package ru.kata.spring.boot_security.demo.web.exeptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }

}
