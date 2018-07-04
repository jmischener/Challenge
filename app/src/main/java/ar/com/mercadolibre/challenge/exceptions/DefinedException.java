package ar.com.mercadolibre.challenge.exceptions;

import android.content.Context;

public class DefinedException extends  Exception {
    private static final int resourceText = 0;
    private final int numberError = 998;

    public DefinedException(Context context) {
        super(context.getResources().getString(resourceText));
    }

    public DefinedException(Context context, Throwable cause) {
        super(context.getResources().getString(resourceText), cause);
    }

    public DefinedException(String msg) {
        super(msg);
    }

    public int getNumberError() {
        return numberError;
    }
}