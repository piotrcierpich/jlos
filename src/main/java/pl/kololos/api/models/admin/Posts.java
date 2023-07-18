package pl.kololos.api.models.admin;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Posts {
    private List<PostInfo> postInfos;
    private final String path;

    public static Posts posts(List<PostInfo> items) {
        return new Posts(items, "/admin/aktualnosci");
    }

    public static Posts galleries(List<PostInfo> items) {
        return new Posts(items, "/admin/galeria");
    }
}
