package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.service.UserService;

import java.util.Arrays;

@Component
public class PostWritingValidator implements Validator {


    public boolean supports(Class<?> clazz) {
        return PostForm.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            PostForm postForm = (PostForm ) target;
            boolean valid = Arrays.stream(postForm.getTags().split(" "))
                    .filter(s -> !s.isEmpty())
                    .allMatch(s -> s.matches("\\w+"));
            if (!valid) {
                errors.rejectValue("tags", "tags.invalid-letters", "tags must consist of latin letters");
            }
        }
    }
}
