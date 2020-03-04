package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.repository.CommentRepository;
import ru.itmo.wp.repository.PostRepository;

import java.util.List;

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

    public void writeComment(User user, long postId, Comment comment) {
        Post post = postRepository.getOne(postId);
        comment.setUser(user);
        post.addComment(comment);
        postRepository.save(post);
    }
}
