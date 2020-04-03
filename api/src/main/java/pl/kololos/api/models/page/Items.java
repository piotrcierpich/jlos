package pl.kololos.api.models.page;

import java.util.List;

public class Items {
    private String title;
    private List<ItemAbstract> itemAbstracts;
    private boolean hasNext;
    private boolean hasPrevious;
    private Integer nextPage;
    private Integer previousPage;

    public Items(String title, List<ItemAbstract> itemAbstracts, boolean hasNext, boolean hasPrevious, Integer nextPage, Integer previousPage) {
        this.title = title;
        this.itemAbstracts = itemAbstracts;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
    }

    public String getTitle() {
        return title;
    }

    public List<ItemAbstract> getItemAbstracts() {
        return itemAbstracts;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public Integer getPreviousPage() {
        return previousPage;
    }
}
