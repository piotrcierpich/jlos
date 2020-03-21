package pl.kololos.api.services;

import org.springframework.stereotype.Component;
import pl.kololos.api.models.ArticleAbstract;
import pl.kololos.api.models.Articles;
import pl.kololos.api.models.Index;
import pl.kololos.api.utils.ResourceFileReader;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Component
public class PageService {
    private String shortText;
    private String longText;

    @PostConstruct
    public void onInit() {
        shortText = ResourceFileReader.readFileContent("/shortText.txt");
        longText = ResourceFileReader.readFileContent("/longText.txt");
    }

    public Index getIndex() {
        return new Index(longText, getLatestAbstracts(3));
    }

    public Articles getArticles() {
        return new Articles("Aktualności", getLatestAbstracts(6));
    }

    private List<ArticleAbstract> getLatestAbstracts(int count) {
        return IntStream.range(0, count)
                .boxed()
                .map(i -> i +1)
                .map(i -> new ArticleAbstract("Interesting subject " + i, shortText, "123", LocalDate.of(2020, 3, 20)))
                .collect(toList());
    }
}
