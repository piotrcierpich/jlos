package pl.kololos.api.models.page;

import java.util.List;

public class Index {
    private String welcomeText;
    private List<ArticleAbstract> abstracts;

    public Index(String welcomeText, List<ArticleAbstract> abstracts) {
        this.welcomeText = welcomeText;
        this.abstracts = abstracts;
    }

    public String getWelcomeText() {
        return welcomeText;
    }

    public List<ArticleAbstract> getAbstracts() {
        return abstracts;
    }
}
