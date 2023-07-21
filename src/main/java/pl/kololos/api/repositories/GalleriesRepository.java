package pl.kololos.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.kololos.api.models.Gallery;

public interface GalleriesRepository extends PagingAndSortingRepository<Gallery, Long> {
}
