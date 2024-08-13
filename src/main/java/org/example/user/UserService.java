package org.example.user;

import org.example.account.AccountService;
import org.example.hibernate.TransactionHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final AccountService accountService;
    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public UserService(
            AccountService accountService,
            SessionFactory sessionFactory,
            TransactionHelper transactionHelper
    ) {
        this.accountService = accountService;
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public User createUser(String login) {
        return transactionHelper.executeInTransaction(() -> {
            var session = sessionFactory.getCurrentSession();

            var existedUser = session.createQuery("FROM User WHERE login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResultOrNull();
            if (existedUser != null) {
                throw new IllegalArgumentException("User already exists with login=%s"
                        .formatted(login));
            }

            User user = new User(null, login, new ArrayList<>());
            session.persist(user);

            accountService.createAccount(user);
            return user;
        });
    }

    public Optional<User> findUserById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            var user = session.get(User.class, id);
            return Optional.ofNullable(user);
        }
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "SELECT u FROM User u LEFT JOIN FETCH u.accountList",
                            User.class
                    )
                    .list();
        }
    }
}
