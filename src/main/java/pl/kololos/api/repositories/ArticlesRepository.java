package pl.kololos.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import pl.kololos.api.models.admin.Article;

public interface ArticlesRepository extends CrudRepository<Article, Integer> {
}
