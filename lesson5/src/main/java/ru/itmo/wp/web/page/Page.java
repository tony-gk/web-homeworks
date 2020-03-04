package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {
    private UserService userService = new UserService();
    protected HttpServletRequest request;

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        this.request = request;
        view.put("userCount", userService.findCount());

        putUser(request, view);
        putMessage(request, view);
    }

    private void setFicitonUser(HttpServletRequest request, Map<String, Object> view) {
        try {
            User user = userService.validateAndFindByLoginOrEmailAndPassword("admin", "admin");
            setUser(user);
        } catch (ValidationException e) {
           //ignored
        }
    }

    protected void after(HttpServletRequest request, Map<String, Object> view) {

    }

    protected void action() {
        //No operations
    }

    protected void setMessage(String message) {
        request.getSession().setAttribute("message", message);
    }

    protected void setUser(User user) {
        request.getSession().setAttribute("user", user);
    }

    protected User getUser() {
        return (User) request.getSession().getAttribute("user");
    }

    private void putUser(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            view.put("user", user);
        }
    }

    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (message != null) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }
}
