package pl.kololos.api.models.admin;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Pagination {
    private Integer next;
    private Integer previous;
    private boolean hasNext;
    private boolean hasPrevious;
}
