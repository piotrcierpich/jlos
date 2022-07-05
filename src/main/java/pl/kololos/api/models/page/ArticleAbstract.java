package pl.kololos.api.models.page;

import pl.kololos.api.models.admin.Post;
import pl.kololos.api.utils.Locals;

import java.time.LocalDate;

public class ArticleAbstract {
    private static final int ABSTRACT_TEXT_LENGTH = 200;
    private static final String ABSTRACT_TEXT_POSTFIX = "... ";

    private final String title;
    private final String content;
    private final String link;
    private final LocalDate publishDate;

    public ArticleAbstract(String title, String content, String link, LocalDate publishDate) {
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

    public static ArticleAbstract fromPost(Post post) {
        String abstractContent = abstractContent(post);
        LocalDate publishDate = LocalDate.ofInstant(post.getPublishDateTime(), Locals.ZONE_ID);
        return new ArticleAbstract(post.getTitle(), abstractContent, post.getLink(), publishDate);
    }


    private static String abstractContent(Post post) {
        String postContent = post.getContent();
        if(postContent == null) {
            return "";
        }

        int contentLength = Math.min(ABSTRACT_TEXT_LENGTH, postContent.length() - 1);
        return postContent.substring(0, contentLength) + ABSTRACT_TEXT_POSTFIX;
    }
}
