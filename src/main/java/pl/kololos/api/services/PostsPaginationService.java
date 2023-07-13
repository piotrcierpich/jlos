package pl.kololos.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.kololos.api.models.admin.Pagination;
import pl.kololos.api.repositories.PostsPageableRepository;

@Service
@RequiredArgsConstructor
public class PostsPaginationService {
    private final PostsPageableRepository postsPageableRepository;
    public static final int PAGE_SIZE = 30;
    private final static Pagination SINGLE_PAGE = Pagination.builder().hasNext(false).hasPrevious(false).build();

    public Pagination getForArticles(int currentPage) {
        int totalPages = postsPageableRepository.findAll(PageRequest.of(currentPage, PAGE_SIZE)).getTotalPages();
        boolean noRecordsOrSinglePage = totalPages == 0 || totalPages == 1;
        if (noRecordsOrSinglePage) {
            return SINGLE_PAGE;
        }
        boolean isLastPage = currentPage + 1 == totalPages;
        if (isLastPage) {
            return
                    Pagination
                            .builder()
                            .hasNext(false)
                            .hasPrevious(true)
                            .previous(currentPage - 1)
                            .build();
        }

        boolean isFirstPage = currentPage == 0;
        if (isFirstPage) {
            return
                    Pagination
                            .builder()
                            .hasNext(true)
                            .hasPrevious(false)
                            .next(currentPage + 1)
                            .build();
        }
        return
                Pagination
                        .builder()
                        .hasNext(true)
                        .hasPrevious(true)
                        .next(currentPage + 1)
                        .previous(currentPage - 1)
                        .build();
    }
}
