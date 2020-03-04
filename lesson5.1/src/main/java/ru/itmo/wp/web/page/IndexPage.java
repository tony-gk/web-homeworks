package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IndexPage extends Page {
    private final ArticleService articleService = new ArticleService();
    private final UserService userService = new UserService();

    void findAllArticles(Map<String, Object> view) {
        List<Article> notHiddenArticles = articleService.findAllNotHidden();
        List<String> articleAuthors = new ArrayList<>();
        for (Article article : notHiddenArticles) {
            articleAuthors.add(userService.findById(article.getUserId()).getLogin());
        }
        view.put("articles", notHiddenArticles);
        view.put("articleAuthors", articleAuthors);
    }
}
