package pl.kololos.api.models;

public class ItemAbstract {
    private String title;
    private String link;

    public ItemAbstract(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
