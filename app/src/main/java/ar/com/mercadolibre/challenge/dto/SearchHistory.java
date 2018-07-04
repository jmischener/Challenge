package ar.com.mercadolibre.challenge.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchHistory {
    private String searchQuery;
    private List<SearchHistoryItem> items;

    public SearchHistory(){
        this.items = new ArrayList<>();
    }
    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<SearchHistoryItem> getItems() {
        return items;
    }

    public void setItems(List<SearchHistoryItem> items) {
        this.items = items;
    }
}
