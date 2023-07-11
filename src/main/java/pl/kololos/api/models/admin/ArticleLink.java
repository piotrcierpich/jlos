package pl.kololos.api.models.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kololos.api.repositories.PostsRepository;
import pl.kololos.api.utils.NormalizeLink;

@Service
@RequiredArgsConstructor
public class ArticleLink {
    private final PostsRepository postsRepository;

    public String generate(Post post) {
        return NormalizeLink.execute(post.getTitle());
    }
}
