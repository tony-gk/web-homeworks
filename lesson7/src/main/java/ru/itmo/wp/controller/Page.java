package ru.itmo.wp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itmo.wp.domain.Notice;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.NoticeService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class Page {
    private static final String USER_ID_SESSION_KEY = "userId";
    private static final String MESSAGE_SESSION_KEY = "message";
    private static final String MESSAGETYPE_SESSION_KEY = "messageType";

    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;

    @ModelAttribute("notices")
    public List<Notice> getNotices() {
        return noticeService.findAll();
    }
    @ModelAttribute("user")
    public User getUser(HttpSession httpSession) {
        return userService.findById(getUserId(httpSession));
    }

    @ModelAttribute("message")
    public String getMessage(HttpSession httpSession) {
        String message = (String) httpSession.getAttribute(MESSAGE_SESSION_KEY);
        httpSession.removeAttribute(MESSAGE_SESSION_KEY);
        return message;
    }

    @ModelAttribute("messageType")
    public String getMessageType(HttpSession httpSession) {
        String messageType = (String) httpSession.getAttribute(MESSAGETYPE_SESSION_KEY);
        httpSession.removeAttribute(MESSAGETYPE_SESSION_KEY);
        return messageType;
    }

    void setUser(HttpSession httpSession, User user) {
        if (user != null) {
            httpSession.setAttribute(USER_ID_SESSION_KEY, user.getId());
        } else {
            unsetUser(httpSession);
        }
    }

    Long getUserId(HttpSession httpSession) {
        return (Long) httpSession.getAttribute(USER_ID_SESSION_KEY);
    }

    void unsetUser(HttpSession httpSession) {
        httpSession.removeAttribute(USER_ID_SESSION_KEY);
    }

    void putMessage(HttpSession httpSession, String message) {
        httpSession.setAttribute(MESSAGE_SESSION_KEY, message);
        httpSession.setAttribute(MESSAGETYPE_SESSION_KEY, "success");
    }

    void putErrorMessage(HttpSession httpSession, String message) {
        httpSession.setAttribute(MESSAGE_SESSION_KEY, message);
        httpSession.setAttribute(MESSAGETYPE_SESSION_KEY, "error");
    }
}
