package pl.kololos.api.models.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String link;
    private Instant publishDateTime;

    public Post(String title, String content, String link, Instant publishDateTime) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.publishDateTime = publishDateTime;
    }

    public static Post createNew(ContentUpdate contentUpdate, ArticleLink articleLink) {
        Post post = new Post();
        post.content = contentUpdate.getContent();
        post.title = contentUpdate.getTitle();
        post.publishDateTime = Instant.now();
        post.link = articleLink.generate(post);
        return post;
    }

    public void update(ContentUpdate contentUpdate) {
        content = contentUpdate.getContent().trim();
        title = contentUpdate.getTitle();
        publishDateTime = Instant.now();
    }
}
