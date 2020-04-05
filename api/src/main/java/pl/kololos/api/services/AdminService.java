package pl.kololos.api.services;

import org.springframework.stereotype.Component;
import pl.kololos.api.models.admin.Article;
import pl.kololos.api.models.admin.ArticleInfo;
import pl.kololos.api.models.admin.Articles;
import pl.kololos.api.utils.ResourceFileReader;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class AdminService {
    private String shortText;
    private String longText;

    @PostConstruct
    public void onInit() {
        shortText = ResourceFileReader.readFileContent("/shortText.txt");
        longText = ResourceFileReader.readFileContent("/longText.txt");
    }

    public Article getPageArticle(String pageKind) {
        return new Article(pageKind, longText, "/" + pageKind, LocalDate.now());
    }

    public Articles getNews(int page) {
        List<ArticleInfo> articleInfos = IntStream.range(0, page)
                .boxed()
                .map(i -> new ArticleInfo(i.toString(), "Lorem ipsum dolor sit amet, consectetur", LocalDate.now().minus(Period.ofDays(i))))
                .collect(Collectors.toList());
        return new Articles(articleInfos);
    }
}
