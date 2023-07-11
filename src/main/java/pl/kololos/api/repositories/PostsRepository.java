package pl.kololos.api.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.kololos.api.models.admin.Post;

import java.util.List;

public interface PostsRepository extends PagingAndSortingRepository<Post, Long> {
    List<Post> findByOrderByPublishDateTimeDesc(Pageable pageable);
}
