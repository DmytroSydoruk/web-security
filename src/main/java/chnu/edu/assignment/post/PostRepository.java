package chnu.edu.assignment.post;

import org.springframework.data.mongodb.repository.MongoRepository;


/*
    @author     dmytro
    @project    IntelliJ IDEA
    @class      Post
    @version 1.0.0
    @since  24.03.2025 - 18.42     
*/
public interface PostRepository extends MongoRepository<Post, String> {
    
}
