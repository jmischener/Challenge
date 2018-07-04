package ar.com.mercadolibre.challenge.listeners;

public interface ResponseWSListenerWithPaging<T> {
    void onStart();
    void onSuccess(T result, int offset, int total);
    void onFailure(Exception e);
}
