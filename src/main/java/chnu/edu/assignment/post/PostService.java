package chnu.edu.assignment.post;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @author     dmytro
    @project    IntelliJ IDEA
    @class      PostService
    @version 1.0.0
    @since  24.03.2025 - 18.43       
*/
@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    private List<Post> posts;

    @PostConstruct
    void init() {
        posts.add(new Post("1", "title1", "content1"));
        posts.add(new Post("2", "title2", "content2"));
        posts.add(new Post("3", "title3", "content3"));
        postRepository.saveAll(posts);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post getById(String id) {
        return postRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        postRepository.deleteById(id);
    }

    public Post create(Post post) {
        return postRepository.save(post);
    }

    public Post update(String id, Post post) {
        return postRepository.save(post);
    }

}
