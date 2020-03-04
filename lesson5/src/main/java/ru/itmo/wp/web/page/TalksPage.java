package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.TemplateTalk;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TalksPage extends Page {
    private final UserService userService = new UserService();
    private final TalkService talkService = new TalkService();

    void action(Map<String, Object> view) {
        if (getUser() == null) {
            setMessage("You are not authorized to access this page.");
            throw new RedirectException("/index");
        }
        view.put("logged_user", getUser());
        view.put("users", userService.findAll());

        List<Talk> talks = talkService.findAll(getUser());
        List<TemplateTalk> templateTalks = new ArrayList<>();
        for (Talk talk : talks) {
            User sourceUser = userService.find(talk.getSourceUserId());
            User targetUser = userService.find(talk.getTargetUserId());
            TemplateTalk templateTalk = new TemplateTalk(talk.getId(), talk.getText(), talk.getCreationTime());
            templateTalk.setSourceUserLogin(sourceUser.getLogin());
            templateTalk.setTargetUserLogin(targetUser.getLogin());
            templateTalks.add(templateTalk);
        }

        view.put("talks", templateTalks);
    }

    void send(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        Talk talk = new Talk();
        talk.setText(request.getParameter("text"));
        talk.setSourceUserId(getUser().getId());
        talk.setTargetUserId(Long.parseLong(request.getParameter("targetUserId")));
        talkService.save(talk);

        setMessage("Message sent successfully!");
        throw new RedirectException("/talks");
    }
}
