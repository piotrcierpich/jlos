package pl.kololos.api.models.admin;

import lombok.Data;
import pl.kololos.api.models.Gallery;
import pl.kololos.api.models.Image;

import java.util.Set;

@Data
public class GalleryInfo {
    private Set<Image> images;
    private Long galleryId;
    private String path;
    private String name;

    public static GalleryInfo forGallery(Gallery gallery) {
        GalleryInfo galleryInfo = new GalleryInfo();
        galleryInfo.setGalleryId(gallery.getId());
        galleryInfo.setImages(gallery.getImages());
        galleryInfo.setPath("/admin/galeria/" + gallery.getId());
        galleryInfo.setName(galleryInfo.getName());
        return galleryInfo;
    }
}
