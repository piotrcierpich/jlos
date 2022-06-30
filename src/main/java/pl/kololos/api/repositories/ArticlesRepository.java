package pl.kololos.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.kololos.api.models.admin.Article;

public interface ArticlesRepository extends PagingAndSortingRepository<Article, Long> {
}
