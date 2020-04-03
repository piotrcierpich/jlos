package pl.kololos.api.services;

import org.springframework.stereotype.Component;
import pl.kololos.api.models.admin.Article;
import pl.kololos.api.utils.ResourceFileReader;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

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
}
