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
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String link;
    private Instant publishDateTime;

    public Article(String title, String content, String link, Instant publishDateTime) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.publishDateTime = publishDateTime;
    }

    public static Article createNew(ArticleUpdate articleUpdate, ArticleLink articleLink) {
        Article article = new Article();
        article.content = articleUpdate.getContent();
        article.title = articleUpdate.getTitle();
        article.publishDateTime = Instant.now();
        article.link = articleLink.generate(article);
        return article;
    }

    public void update(ArticleUpdate articleUpdate) {
        content = articleUpdate.getContent().trim();
        title = articleUpdate.getTitle();
    }
}
