package pl.kololos.api.models.admin;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostInfo {
    private final String title;
    private final Long id;
    private final LocalDate publishDate;

    public PostInfo(Long id, String title, LocalDate publishDate) {
        this.title = title;
        this.id = id;
        this.publishDate = publishDate;
    }
}
