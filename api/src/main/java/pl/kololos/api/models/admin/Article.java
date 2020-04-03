package pl.kololos.api.models.admin;

import java.time.LocalDate;

public class Article {
    private final String title;
    private final String content;
    private final String link;
    private final LocalDate publishDate;

    public Article(String title, String content, String link, LocalDate publishDate) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }
}
