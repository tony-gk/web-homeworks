package ru.itmo.wp.model.repository.impl;

import com.google.common.collect.Iterables;
import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.Entity;
import ru.itmo.wp.model.repository.ArticleRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class ArticleRepositoryImpl extends AbstractRepository<Article> implements ArticleRepository {
    private final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public void save(Article article) {
        save(article, List.of(Map.entry("userId", article.getUserId()),
                Map.entry("title", article.getTitle()),
                Map.entry("text", article.getText())));
    }

    @Override
    public List<Article> findAll() {
        return findAll("SELECT * FROM Article ORDER BY creationTime");
    }

    @Override
    public List<Article> findAllByUserId(long userId) {
        return findAll("SELECT * FROM Article WHERE userId = ?", List.of(userId));
    }

    @Override
    public void updateHidden(Long articleId, Boolean hidden) {
        update("UPDATE `Article` SET `hidden` = ? WHERE `Article`.`id` = ?",
                List.of(hidden, articleId));
    }

    @Override
    public Article findById(long articleId) {
        return find("SELECT * FROM Article WHERE id=?", List.of(articleId));
    }

    @Override
    public List<Article> findAllByHidden(Boolean hidden) {
        return findAll("SELECT * FROM Article WHERE hidden = ? ORDER BY creationTime DESC",
                List.of(hidden));
    }

    @Override
    protected Article toThis(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Article article = new Article();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    article.setId(resultSet.getLong(i));
                    break;
                case "userId":
                    article.setUserId(resultSet.getLong(i));
                    break;
                case "title":
                    article.setTitle(resultSet.getString(i));
                    break;
                case "text":
                    article.setText(resultSet.getString(i));
                    break;
                case "hidden":
                    article.setHidden(resultSet.getBoolean(i));
                    break;
                case "creationTime":
                    article.setCreationTime(resultSet.getTimestamp(i));
                    break;
            }
        }
        return article;
    }

    @Override
    protected String getEntityName() {
        return "Article";
    }

}
