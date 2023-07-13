package pl.kololos.api.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kololos.api.models.admin.Post;

public interface PostsRepository extends CrudRepository<Post, Long> {
    Post findByLink(String link);
}
