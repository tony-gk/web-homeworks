package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @PostMapping("/users/disable")
    public String updateDisabled(HttpSession httpSession,
                                 @RequestParam boolean disabledValue,
                                 @RequestParam long userId) {
        User user = getUser(httpSession);
        if (user != null && !user.isDisabled()) {
            userService.updateDisabled(userId, disabledValue);

            if (getUserId(httpSession) == userId) {
                putMessage(httpSession, "Congrats, you have been disabled yourself!");
                unsetUser(httpSession);
            }
        } else {
            putErrorMessage(httpSession, "You can not do this!");
        }
        return "redirect:/users/all";
    }
}
