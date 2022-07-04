package pl.kololos.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.kololos.api.models.admin.Post;

public interface PostsRepository extends PagingAndSortingRepository<Post, Long> {
}
