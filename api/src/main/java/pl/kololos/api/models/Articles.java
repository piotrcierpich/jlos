package pl.kololos.api.models;

import java.util.List;

public class Articles {
    private String title;
    private List<ArticleAbstract> abstracts;

    public Articles(String title, List<ArticleAbstract> abstracts) {
        this.title = title;
        this.abstracts = abstracts;
    }

    public String getTitle() {
        return title;
    }

    public List<ArticleAbstract> getAbstracts() {
        return abstracts;
    }
}
