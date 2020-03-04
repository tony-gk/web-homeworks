package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public void save(PostForm form, User user) {
        Post post = new Post();
        post.setText(form.getText());
        post.setTitle(form.getTitle());
        post.setUser(user);
        postRepository.save(post);
    }

    public Optional<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }

    public long countById(Long postId) {
        return postRepository.countById(postId);
    }
}
