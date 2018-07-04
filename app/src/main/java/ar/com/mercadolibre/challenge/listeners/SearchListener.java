package ar.com.mercadolibre.challenge.listeners;

import ar.com.mercadolibre.challenge.dto.SearchHistoryItem;

public interface SearchListener {
    void onItemClick(String title, SearchHistoryItem item);
    void onDeleteSearch(String title);
}
