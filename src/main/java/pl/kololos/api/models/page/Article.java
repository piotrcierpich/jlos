package pl.kololos.api.models.page;

import pl.kololos.api.models.admin.Post;

public class Article {
    private String title;
    private String content;

    public Article() {
    }

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Article fromPost(Post post) {
        return new Article(post.getTitle(), post.getContent());
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
