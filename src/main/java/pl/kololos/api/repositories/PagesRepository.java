package pl.kololos.api.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kololos.api.models.Page;

import java.util.Optional;

public interface PagesRepository extends CrudRepository<Page, Long> {
    Optional<Page> findByKind(String kind);
}
