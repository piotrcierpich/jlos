package pl.kololos.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.kololos.api.models.admin.*;
import pl.kololos.api.repositories.ArticlesRepository;
import pl.kololos.api.utils.ResourceFileReader;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
@RequiredArgsConstructor
public class AdminService {
    private String shortText;
    private String longText;

    private final ArticlesRepository articlesRepository;

    private final ArticleLink articleLink;

    @SuppressWarnings("unused")
    @PostConstruct
    public void onInit() {
        shortText = ResourceFileReader.readFileContent("/shortText.txt");
        longText = ResourceFileReader.readFileContent("/longText.txt");
    }

    public Article getArticleByKind(String pageKind) {
        return new Article(pageKind, longText, "/" + pageKind, Instant.now());
    }

    public Articles getNews(int page) {
        Page<Article> articles = articlesRepository.findAll(PageRequest.of(page, ArticlesPaginationService.PAGE_SIZE));
        List<ArticleInfo> articleInfos = articles.get()
                .map(i -> new ArticleInfo(i.getId(), i.getTitle(), i.getPublishDateTime().atZone(ZoneId.of("Europe/Warsaw")).toLocalDate()))
                .collect(Collectors.toList());
        return new Articles(articleInfos);
    }

    public Optional<Article> getArticleById(Long articleId) {
        return articlesRepository.findById(articleId);
    }

    public Article saveArticle(ArticleUpdate articleUpdate) {
        Article article = Article.createNew(articleUpdate, articleLink);
        Article savedArticle = articlesRepository.save(article);
        return savedArticle;
    }

    public Article updateArticle(Long id, ArticleUpdate articleUpdate) {
        Optional<Article> articleIfFound = getArticleById(id);
        if (articleIfFound.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find article");
        }
        Article articleToUpdate = articleIfFound.get();
        articleToUpdate.update(articleUpdate);
        return articlesRepository.save(articleToUpdate);
    }
}
