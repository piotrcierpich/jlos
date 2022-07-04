package pl.kololos.api.models.admin;

import lombok.Getter;

import java.util.List;

@Getter
public class Posts {
    private List<PostInfo> postInfos;

    public Posts(List<PostInfo> postInfos) {
        this.postInfos = postInfos;
    }
}
