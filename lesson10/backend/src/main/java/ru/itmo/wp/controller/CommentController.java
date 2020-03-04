package ru.itmo.wp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.CommentForm;
import ru.itmo.wp.service.CommentService;
import ru.itmo.wp.util.BindingResultUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class CommentController extends ApiController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public List<Comment> getComments(@RequestParam Long postId) {
         return commentService.findAllByPostId(postId);
    }

    @PostMapping("/comments")
    public void writeComment(@RequestBody @Valid CommentForm commentForm, BindingResult bindingResult, User user) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }
        if (user == null) {
            throw new ValidationException("You must be logged in");
        }
        commentService.writeComment(user, commentForm);

    }
}
