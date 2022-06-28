package pl.kololos.api.models.admin;

import java.util.List;

public class Articles {
    private List<ArticleInfo> articleInfos;

    public Articles(List<ArticleInfo> articleInfos) {
        this.articleInfos = articleInfos;
    }

    public List<ArticleInfo> getArticleInfos() {
        return articleInfos;
    }
}
