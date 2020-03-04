package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class MyArticlesPage extends Page {
    private final ArticleService articleService = new ArticleService();

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        if (getUser() == null) {
            setMessage("You are not authorized to access this page.");
            throw new RedirectException("/index");
        }
    }
    private void action(Map<String, Object> view) {
        List<Article> articles = articleService.findAllByUserId(getUser().getId());
        view.put("articles", articles);
    }

    private void setHiddenValue(HttpServletRequest request) {
        long articleId = Long.parseLong(request.getParameter("articleId"));
        if (articleService.findById(articleId).getUserId() != getUser().getId()) {
            return;
        }
        boolean hidden = Boolean.parseBoolean(request.getParameter("hiddenValue"));
        articleService.setHidden(articleId, hidden);
    }
}
