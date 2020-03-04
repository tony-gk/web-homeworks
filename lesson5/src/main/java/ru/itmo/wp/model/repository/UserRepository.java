package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface UserRepository {
    void save(User user, String passwordSha);
    User findById(long id);
    User findByEmail(String email);
    User findByLogin(String login);
    User findByLoginOrEmailAndPasswordSha(String loginOrEmail, String passwordSha);
    List<User> findAll();
    Long findCount();

}
