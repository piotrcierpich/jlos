package pl.kololos.api.models;

import lombok.Getter;
import pl.kololos.api.models.admin.ContentUpdate;
import pl.kololos.api.models.admin.PageKind;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String link;
    private Instant publishDateTime;
    @Column(unique = true, length = 25)
    private String kind;

    public static Page newPage(PageKind kind) {
        Page page = new Page();
        page.kind = kind.getKindName();
        page.title = kind.getTitle();
        return page;
    }

    public void update(ContentUpdate contentUpdate) {
        content = contentUpdate.getContent().trim();
        title = contentUpdate.getTitle();
        publishDateTime = Instant.now();
    }
}
