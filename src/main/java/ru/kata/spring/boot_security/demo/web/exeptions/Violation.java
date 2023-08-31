package ru.kata.spring.boot_security.demo.web.exeptions;

public class Violation {
    private final String fieldName;
    private final String message;

    public Violation(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Поле - %s %s",fieldName,message);
    }
}
