package pl.kololos.api.models;

import lombok.Data;
import pl.kololos.api.models.admin.GalleryUpdate;
import pl.kololos.api.utils.NormalizeLink;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String galleryName;
    private String link;
    private Instant publishDateTime;

    public static Gallery createNew(GalleryUpdate galleryUpdate) {
        Gallery gallery = new Gallery();
        gallery.setGalleryName(galleryUpdate.getTitle());
        gallery.publishDateTime = Instant.now();
        gallery.link = NormalizeLink.execute(galleryUpdate.getTitle());
        return gallery;
    }
}
