package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class LogoutPage extends Page {
    private final EventService eventService = new EventService();

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        if (getUser() == null) {
            throw new RedirectException("/index");
        }
    }
    private void action(HttpServletRequest request) {
        eventService.save(new Event(getUser().getId(), Event.Type.LOGOUT));

        setUser(null);

        setMessage("Good bye. Hope to see you soon!");
        throw new RedirectException("/index");
    }
}
