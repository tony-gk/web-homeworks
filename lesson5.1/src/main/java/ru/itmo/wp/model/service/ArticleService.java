package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateCreation(Article article, User loggedUser) throws ValidationException {
        if (loggedUser == null) {
            throw new ValidationException("You must be authorized to create an article");
        }
        if (Strings.isNullOrEmpty(article.getTitle())) {
            throw new ValidationException("Title is required.");
        }
        if (article.getTitle().length() > 255) {
            throw new ValidationException("Title can't be longer than 255 symbols.");
        }

        if (Strings.isNullOrEmpty(article.getText())) {
            throw new ValidationException("Text is empty.");
        }
        if (article.getText().length() > (1 << 16) - 1) {
            throw new ValidationException("Text can't be longer than " + ((1 << 16) - 1) + " symbols.");
        }
    }

    public Article findById(long articleId) {
        return  articleRepository.findById(articleId);
    }
    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findAllByUserId(long userId) {
        return articleRepository.findAllByUserId(userId);
    }

    public void setHidden(Long articleId, Boolean hidden) {
        articleRepository.updateHidden(articleId, hidden);
    }

    public List<Article> findAllNotHidden() {
        return articleRepository.findAllByHidden(false);
    }
}
