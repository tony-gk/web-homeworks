package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;

public class ArticlePage extends Page {
    private final ArticleService articleService = new ArticleService();

    void create(HttpServletRequest request) throws ValidationException {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setText(request.getParameter("text"));

        articleService.validateCreation(article, getUser());

        article.setUserId(getUser().getId());
        articleService.save(article);

        setMessage("Article created successfully!");
        throw new RedirectException("/article");
    }
}
