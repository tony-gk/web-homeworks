package ru.itmo.wp.web.page;

import org.mariadb.jdbc.tls.TlsSocketPlugin;
import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.EventRepository;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;

public class LogoutPage extends Page {
    private final EventService eventService = new EventService();

    private void action(HttpServletRequest request) {
        eventService.save(new Event(getUser().getId(), Event.Type.LOGOUT));

        setUser(null);

        setMessage("Good bye. Hope to see you soon!");
        throw new RedirectException("/index");
    }
}
