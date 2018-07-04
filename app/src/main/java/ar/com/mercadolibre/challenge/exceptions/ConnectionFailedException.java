package ar.com.mercadolibre.challenge.exceptions;

import android.content.Context;

import ar.com.mercadolibre.challenge.R;


public class ConnectionFailedException extends  Exception {
    private static final int resourceText = R.string.error_connection_failed;
    private final int numberError = 1;

    public ConnectionFailedException(Context context) {
        super(context.getResources().getString(resourceText));
    }

    public ConnectionFailedException(String msg) {
        super(msg);
    }

    public int getNumberError() {
        return numberError;
    }
}

