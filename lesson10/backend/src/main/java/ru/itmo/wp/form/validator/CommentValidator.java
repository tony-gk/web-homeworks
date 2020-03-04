package ru.itmo.wp.form.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.CommentForm;
import ru.itmo.wp.service.PostService;

@Component
public class CommentValidator implements Validator {
    private final PostService postService;

    @Autowired
    public CommentValidator(PostService postService) {
        this.postService = postService;
    }

    public boolean supports(Class<?> clazz) {
        return CommentValidator.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            CommentForm commentForm = (CommentForm) target;
            if (postService.countById(commentForm.getPostId()) == 0) {
                errors.reject("post.invalid-post", "Invalid post id");
            }
        }
    }


}
