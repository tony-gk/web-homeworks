package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.CommentService;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    final private PostService postService;
    final private CommentService commentService;

    public PostPage(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @Guest
    @GetMapping("/post/{id}")
    public String postPage(@PathVariable long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("comment", new Comment());
        return "PostPage";
    }

    @PostMapping("/post/writeComment")
    public String writeComment(@Valid @ModelAttribute("comment") Comment comment,
                               BindingResult bindingResult,
                               @RequestParam("postId") Long postId,
                               Model model,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postService.findById(postId));
            return "PostPage";
        }

        commentService.writeComment(getUser(httpSession), postId, comment);
        model.addAttribute("post", comment.getPost());
        putMessage(httpSession, "You have posted new comment");

        return "redirect:/post/" + postId;
    }
}
