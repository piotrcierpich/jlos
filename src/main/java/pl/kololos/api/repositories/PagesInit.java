package pl.kololos.api.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kololos.api.models.Page;
import pl.kololos.api.models.admin.PageKind;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagesInit {
    private final PagesRepository pagesRepository;

    @SuppressWarnings("unused")
    @PostConstruct
    void initializePages() {
        for (PageKind pageKind : PageKind.values()) {
            createIfNotExists(pageKind);
        }
    }

    private void createIfNotExists(PageKind pageKind) {
        Optional<Page> page = pagesRepository.findByKind(pageKind.getKindName());
        if (page.isPresent()) {
            return;
        }
        Page newPage = Page.newPage(pageKind);
        pagesRepository.save(newPage);
    }
}
