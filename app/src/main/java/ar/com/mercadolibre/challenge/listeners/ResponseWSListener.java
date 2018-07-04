package ar.com.mercadolibre.challenge.listeners;

public interface ResponseWSListener<T> {
    void onStart();
    void onSuccess(T result);
    void onFailure(Exception e);
}
