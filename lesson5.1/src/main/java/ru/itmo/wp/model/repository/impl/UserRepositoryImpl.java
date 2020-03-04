package ru.itmo.wp.model.repository.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.database.StatementParameter;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {
    private final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();


    @Override
    public void save(User user, String passwordSha) {
        save(user, List.of(Map.entry("login", user.getLogin()),
                Map.entry("email", user.getEmail()),
                Map.entry("admin", user.isAdmin()),
                Map.entry("passwordSha", passwordSha)));
    }

    @Override
    public void updateAdmin(long userId, boolean adminValue) {
        update("UPDATE `User` SET `admin` = ? WHERE `User`.`id` = ?",
                List.of(adminValue, userId));
    }

    @Override
    public User findById(long id) {
        return find("SELECT * FROM User WHERE id = ?", List.of(id));
    }

    @Override
    public User findByEmail(String email) {
        return find("SELECT * FROM User WHERE email = ?", List.of(email));
    }

    @Override
    public User findByLogin(String login) {
        return find("SELECT * FROM User WHERE login = ?", List.of(login));
    }

    @Override
    public User findByLoginOrEmailAndPasswordSha(String loginOrEmail, String passwordSha) {
        return find("SELECT * FROM User WHERE (login=? OR email=?) AND passwordSha=?",
                List.of(loginOrEmail, loginOrEmail, passwordSha));
    }

    @Override
    public List<User> findAll() {
        return findAll("SELECT * FROM User ORDER BY id DESC");
    }



    @Override
    protected User toThis(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        User user = new User();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    user.setId(resultSet.getLong(i));
                    break;
                case "login":
                    user.setLogin(resultSet.getString(i));
                    break;
                case "email":
                    user.setEmail(resultSet.getString(i));
                    break;
                case "admin":
                    user.setAdmin(resultSet.getBoolean(i));
                    break;
                case "creationTime":
                    user.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return user;
    }

    @Override
    protected String getEntityName() {
        return "User";
    }

}
