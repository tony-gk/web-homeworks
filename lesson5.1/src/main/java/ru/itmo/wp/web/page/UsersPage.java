package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit;
import java.util.Map;

public class UsersPage extends Page {
    private final UserService userService = new UserService();

    private void action(Map<String, Object> view) {
        view.put("loggedAsAdmin", getUser() != null && userService.isAdmin(getUser().getId()));
    }
    private void findAll(Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) {
        view.put("user", userService.findById(Long.parseLong(request.getParameter("userId"))));
    }

    private void setAdmin(HttpServletRequest request) throws ValidationException {
        if (getUser() == null || !userService.isAdmin(getUser().getId())) {
            throw new ValidationException("You are not admin.");
        }

        long userId = Long.parseLong(request.getParameter("userId"));
        boolean adminValue = Boolean.parseBoolean(request.getParameter("adminValue"));
        userService.setAdmin(userId, adminValue);
    }
}
