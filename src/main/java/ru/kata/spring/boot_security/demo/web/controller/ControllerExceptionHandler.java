package ru.kata.spring.boot_security.demo.web.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kata.spring.boot_security.demo.web.exceptions.DeleteException;
import ru.kata.spring.boot_security.demo.web.exceptions.UserEmailException;
import ru.kata.spring.boot_security.demo.web.exceptions.Violation;

import javax.validation.ConstraintViolationException;
import java.util.List;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserEmailException.class })
    ResponseEntity<String> handleUserNameDuplicated(UserEmailException ex) {
        return new ResponseEntity( ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> onConstraintValidationException(
            ConstraintViolationException e) {
        final List<String> violations = e.getConstraintViolations().stream()
                .map(violation -> String.valueOf(new Violation(violation.getPropertyPath().toString(),violation.getMessage())))
                .toList();
        return new ResponseEntity( violations, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DeleteException.class)
    ResponseEntity<String> onException(DeleteException e) {
        return new ResponseEntity( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
