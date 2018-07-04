package ar.com.mercadolibre.challenge.fragments;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

public class MCFragment<T> extends Fragment {
    protected T _listener;

    /***************************************
     *            GUI METHODS
     ***************************************/
    public void setListener(T listener) {
        _listener = listener;
    }


    /***************************************
     *          ERROR METHODS
     ***************************************/
    // evento de excepcion por parte del WS
    protected void undefinedFailedEvent(String message) {
        try {
            Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                    .show();
        } catch (Exception e) {
        }
    }
}
