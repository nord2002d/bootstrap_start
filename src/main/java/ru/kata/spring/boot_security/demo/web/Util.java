package ru.kata.spring.boot_security.demo.web;

import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
public class Util implements CommandLineRunner {
    private SessionFactory sessionFactory;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String SER_NAME = "serNAme";
    private static final String ROLES = "roles";
    private static final String EMAIL = "email";
    @Autowired
    public Util(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void run(String... args) {


        StoredProcedureQuery query1 = sessionFactory.createEntityManager()
                .createStoredProcedureQuery("create_users" )
                .registerStoredProcedureParameter("id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("age", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(EMAIL, String.class,ParameterMode.IN)
                .registerStoredProcedureParameter(USERNAME, String.class,ParameterMode.IN)
                .registerStoredProcedureParameter(PASSWORD, String.class,ParameterMode.IN)
                .registerStoredProcedureParameter(SER_NAME, String.class,ParameterMode.IN)
                .registerStoredProcedureParameter(ROLES, String.class,ParameterMode.IN)
                .setParameter("id", 1L)
                .setParameter("age", 11)
                .setParameter(EMAIL, "admin@a.ru")
                .setParameter(USERNAME, "admin")
                .setParameter(PASSWORD, new BCryptPasswordEncoder().encode("admin"))
                .setParameter(SER_NAME, "Socolov")
                .setParameter(ROLES, "ADMIN");

        try {
            query1.execute();
        } finally {
            query1.unwrap(ProcedureOutputs.class).release();
        }

        StoredProcedureQuery query2 = sessionFactory.createEntityManager()
                .createStoredProcedureQuery("create_users" )
                .registerStoredProcedureParameter("id", Long.class,ParameterMode.IN)
                .registerStoredProcedureParameter("age", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(EMAIL, String.class,ParameterMode.IN)
                .registerStoredProcedureParameter(USERNAME, String.class,ParameterMode.IN)
                .registerStoredProcedureParameter(PASSWORD, String.class,ParameterMode.IN)
                .registerStoredProcedureParameter(SER_NAME, String.class,ParameterMode.IN)
                .registerStoredProcedureParameter(ROLES, String.class,ParameterMode.IN)
                .setParameter("id", 2L)
                .setParameter("age", 12)
                .setParameter(EMAIL, "user@u.ru")
                .setParameter(USERNAME, "user")
                .setParameter(PASSWORD, new BCryptPasswordEncoder().encode("user"))
                .setParameter(SER_NAME, "Ivanov")
                .setParameter(ROLES, "USER");
        try {
            query2.execute();
        } finally {
            query2.unwrap(ProcedureOutputs.class).release();
        }

    }
}
