package ar.com.mercadolibre.challenge.activities;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ar.com.mercadolibre.challenge.R;


public class MCActivity extends AppCompatActivity {

    /***************************************
     *            GUI METHODS
     ***************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /***************************************
     *          ERROR METHODS
     ***************************************/
    protected void withoutConnectionEvent() {
        try {
            Snackbar.make(findViewById(android.R.id.content), R.string.error_connection_failed, Snackbar.LENGTH_LONG)
                    .setActionTextColor(ContextCompat.getColor(this, R.color.colorBackground))
                    .setAction(R.string.text_button_open_settings, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openConnectionSettings();
                        }
                    })
                    .show();
        } catch (Exception ignored) { }
    }

    // evento de excepcion por parte del WS
    protected void undefinedFailedEvent(String message) {
        try {
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                    .show();
        } catch (Exception e) { }
    }


    /***************************************
     *          METHODS
     ***************************************/
    private void openConnectionSettings() {
        try {
            startActivityForResult(new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS), 0);
        }
        catch(Exception ignored) { }
    }
}
