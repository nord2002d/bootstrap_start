package ru.kata.spring.boot_security.demo.web.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.web.model.User;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Component
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
         User  result;
        if (user.getId() == null) {
            Session session = sessionFactory.getCurrentSession();
            result = session.get(User.class,session.save(user));
        } else {
            Session session = sessionFactory.getCurrentSession();
            result = (User)session.merge(user);
        }
        return result;
    }
    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.
                getCurrentSession().createQuery("from User");

        return query.getResultList();

    }
    @Override
    public User getUser(long id) {
        String hql = "from User u where u.id =: id";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void removeUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public Optional<User> findByNameLike(String name) {
        String hql = "from User u where u.name =: name";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("name", name);
        if (query.getResultList().isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<User> findByEmailLike(String email) {
        String hql = "from User u where u.email =: email";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("email", email);
        if (query.getResultList().isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(query.getSingleResult());
    }


}
