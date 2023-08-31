package ru.kata.spring.boot_security.demo.web.exeptions;

public class UserEmailException extends Exception {

    public UserEmailException(String message) {
        super(message);
    }

}
