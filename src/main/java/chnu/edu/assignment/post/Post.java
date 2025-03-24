package chnu.edu.assignment.post;

import lombok.Data;
import org.springframework.data.annotation.Id;

/*
    @author     dmytro
    @project    IntelliJ IDEA
    @class      Post
    @version 1.0.0
    @since  24.03.2025 - 18.29       
*/
@Data
public class Post {
    @Id
    private String id;
    private String title;
    private String content;
}
