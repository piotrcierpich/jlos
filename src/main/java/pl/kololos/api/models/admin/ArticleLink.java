package pl.kololos.api.models.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kololos.api.repositories.PostsPageableRepository;
import pl.kololos.api.utils.NormalizeLink;

@Service
@RequiredArgsConstructor
public class ArticleLink {
    private final PostsPageableRepository postsPageableRepository;

    public String generate(Post post) {
        return NormalizeLink.execute(post.getTitle());
    }
}
