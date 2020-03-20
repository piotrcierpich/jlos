package pl.kololos.api.models;

public class ArticleAbstract {
    private final String title;
    private final String content;
    private final String link;

    public ArticleAbstract(String title, String content, String link) {
        this.title = title;
        this.content = content;
        this.link = link;
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
}
