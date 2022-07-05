package pl.kololos.api.models.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

@Getter
public enum  PageKind {
    UCHWALY ("Uchwały"),
    HISTORIA ("Historia Koła"),
    GLOWNA ("Strona główna"),
    KONTAKT ("Kontakt");

    private final String title;
    private final String kindName;

    PageKind(String title) {
        this.title = title;
        this.kindName = this.name().toLowerCase(Locale.ROOT);
    }
}
