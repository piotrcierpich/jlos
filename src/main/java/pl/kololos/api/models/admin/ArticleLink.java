package pl.kololos.api.models.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kololos.api.repositories.ArticlesRepository;

@Service
@RequiredArgsConstructor
public class ArticleLink {
    private final ArticlesRepository articlesRepository;

    public String generate(Article article) {
        return "abc";
    }
}
