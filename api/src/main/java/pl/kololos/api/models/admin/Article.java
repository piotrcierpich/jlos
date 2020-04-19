package pl.kololos.api.models.admin;

import java.time.LocalDateTime;

public class Article {
    private final String title;
    private final String content;
    private final String link;
    private final LocalDateTime publishDateTime;

    public Article(String title, String content, String link, LocalDateTime publishDateTime) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.publishDateTime = publishDateTime;
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

    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }
}
