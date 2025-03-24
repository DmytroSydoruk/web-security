package chnu.edu.assignment.post;

import lombok.*;
import org.springframework.data.annotation.Id;

/*
    @author     dmytro
    @project    IntelliJ IDEA
    @class      Post
    @version 1.0.0
    @since  24.03.2025 - 18.29       
*/
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Post {
    @Id
    private String id;
    private String title;
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Post post)) return false;
        return getId().equals(post.getId());
    }

    @Override
    public final int hashCode() {
        return getId().hashCode();
    }

}
