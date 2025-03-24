package chnu.edu.assignment.post;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    @author     dmytro
    @project    IntelliJ IDEA
    @class      PostRestController
    @version 1.0.0
    @since  24.03.2025 - 18.45       
*/
@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostRestController {
    private final PostService postService;

    @GetMapping
    public List<Post> getPosts() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public Post getOneById(@PathVariable String id) {
        return postService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        postService.deleteById(id);
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable String id, @RequestBody Post post) {
        return postService.update(id, post);
    }

    @GetMapping("/hello")
    public String hello() { 
        return "Hello, World!";
    }
}
