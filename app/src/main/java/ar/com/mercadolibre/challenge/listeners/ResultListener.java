package ar.com.mercadolibre.challenge.listeners;

import ar.com.mercadolibre.challenge.dto.Result;

public interface ResultListener {
    void onItemClick(Result item);
    void onLoadMoreData(int offset);
}
