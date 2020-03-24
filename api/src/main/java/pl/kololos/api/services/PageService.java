package pl.kololos.api.services;

import org.springframework.stereotype.Component;
import pl.kololos.api.models.*;
import pl.kololos.api.utils.ResourceFileReader;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
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
                .map(i -> i + 1)
                .map(i -> new ArticleAbstract("Interesting subject " + i, shortText, "123", LocalDate.of(2020, 3, 20)))
                .collect(toList());
    }

    public Items getGalleries(Integer page) {
        List<ItemAbstract> itemAbstracts = new ArrayList<>();
        itemAbstracts.add(new ItemAbstract("Lorem ipsum dolor sit amet 1", "1"));
        itemAbstracts.add(new ItemAbstract("Lorem ipsum dolor sit amet 2", "2"));
        itemAbstracts.add(new ItemAbstract("Lorem ipsum dolor sit amet 3", "3"));
        itemAbstracts.add(new ItemAbstract("Lorem ipsum dolor sit amet 4", "galeria_4"));
        itemAbstracts.add(new ItemAbstract("Lorem ipsum dolor sit amet 5", "galeria_5"));
        itemAbstracts.add(new ItemAbstract("Lorem ipsum dolor sit amet 6", "galeria_6"));
        boolean hasPrevious = page != null && page > 1;
        boolean hasNext = page == null || page < 3;
        Integer nextPage = hasNext
                ? page == null ? 2 : page + 1
                : null;
        Integer previousPage = hasPrevious ? page - 1 : null;
        return new Items("Galeria", itemAbstracts, hasNext, hasPrevious, nextPage, previousPage);
    }
}
