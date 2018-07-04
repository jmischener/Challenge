package ar.com.mercadolibre.challenge.exceptions;

import android.content.Context;
import ar.com.mercadolibre.challenge.R;

public class UndefinedException extends  Exception {
    private static final int resourceText = R.string.error_undefined;
    private final int numberError = 999;

    public UndefinedException(Context context) {
        super(context.getResources().getString(resourceText));
    }

    public UndefinedException(Context context, Throwable cause) {
        super(context.getResources().getString(resourceText), cause);
    }

    public UndefinedException(String msg) {
        super(msg);
    }

    public int getNumberError() {
        return numberError;
    }
}


