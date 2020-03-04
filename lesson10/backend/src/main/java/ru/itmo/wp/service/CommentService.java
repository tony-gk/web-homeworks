package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.CommentForm;
import ru.itmo.wp.repository.CommentRepository;
import ru.itmo.wp.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    public List<Comment> findAll() {
        return commentRepository.findAllByOrderByCreationTimeDesc();
    }

    public void writeComment(User user, CommentForm commentForm) {
        Comment comment = new Comment();
        Post post = postRepository.getOne(commentForm.getPostId());
        comment.setText(commentForm.getText());
        comment.setUser(user);
        comment.setPost(post);
        post.addComment(comment);
        postRepository.save(post);
    }

    public List<Comment> findAllByPostId(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            return post.get().getComments();
        } else {
            return new ArrayList<>();
        }
    }
}
