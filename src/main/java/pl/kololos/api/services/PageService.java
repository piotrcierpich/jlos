package pl.kololos.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.kololos.api.models.admin.Page;
import pl.kololos.api.models.page.*;
import pl.kololos.api.repositories.PagesRepository;
import pl.kololos.api.repositories.PostsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
@RequiredArgsConstructor
public class PageService {
    private final PagesRepository pagesRepository;
    private final PostsRepository postsRepository;

    public Index getIndex() {
        Optional<Page> mainPage = pagesRepository.findByKind("glowna");
        if (mainPage.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find page");
        }
        Page main = mainPage.get();
        return new Index(main.getContent(), getLatestAbstracts(3));
    }

    public Articles getArticles() {
        return new Articles("Aktualności", getLatestAbstracts(6));
    }

    private List<ArticleAbstract> getLatestAbstracts(int count) {
        return postsRepository
                .findByOrderByPublishDateTimeDesc(PageRequest.of(0, count))
                .stream()
                .map(ArticleAbstract::fromPost)
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
