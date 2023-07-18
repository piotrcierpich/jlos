package pl.kololos.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.kololos.api.models.Gallery;

import java.util.UUID;

public interface GalleriesRepository extends PagingAndSortingRepository<Gallery, UUID> {
}
