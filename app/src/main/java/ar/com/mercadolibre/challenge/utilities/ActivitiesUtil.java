package ar.com.mercadolibre.challenge.utilities;

import android.content.Intent;

import ar.com.mercadolibre.challenge.activities.MCActivity;

public class ActivitiesUtil {

    public static void moveTo(MCActivity from, Class to, boolean finish) {
        try {
            if(finish) from.finish();

            Intent i = new Intent(from.getBaseContext(), to);
            from.startActivity(i);
        }
        catch(Exception e) { throw e;}
    }

    public static void moveToWithParameters(MCActivity from, Class to, boolean finish, String... parameters) {
        try {
            if(finish) from.finish();

            Intent i = new Intent(from.getBaseContext(), to);
            for(int j=0;j<parameters.length;j++) {
                i.putExtra(parameters[j], parameters[++j]);
            }
            from.startActivity(i);
        }
        catch(Exception e) { throw e;}
    }
}
