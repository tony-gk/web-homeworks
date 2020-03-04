package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface ArticleRepository {
    void save(Article article);

    List<Article> findAll();

    List<Article> findAllByUserId(long UserId);

    void updateHidden(Long articleId, Boolean hidden);

    Article findById(long articleId);

    List<Article> findAllByHidden(Boolean hidden);
}
