package pl.kololos.api.models.admin;

import java.time.LocalDate;

public class ArticleInfo {
    private final String title;
    private final String id;
    private final LocalDate publishDate;

    public ArticleInfo(String id, String title, LocalDate publishDate) {
        this.title = title;
        this.id = id;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public String getId() {
        return id;
    }
}
