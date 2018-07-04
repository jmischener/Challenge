package ar.com.mercadolibre.challenge.utilities;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;

public class NetworkUtil {

    public static boolean isConnected(@NonNull Context base) {
        try {
            ConnectivityManager connMgr = (ConnectivityManager) base.getSystemService(base.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        }
        catch (Exception ignored) { return false; }
    }

    public static boolean isWifiConnected(@NonNull Context base) {
        return isConnected(base, ConnectivityManager.TYPE_WIFI);
    }

    public static boolean isMobileConnected(@NonNull Context base) {
        return isConnected(base, ConnectivityManager.TYPE_MOBILE);
    }

    private static boolean isConnected(@NonNull Context base, int type) {
        ConnectivityManager connMgr = (ConnectivityManager) base.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(type);
            return networkInfo != null && networkInfo.isConnected();
        } else {
            return isConnected(connMgr, type);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static boolean isConnected(@NonNull ConnectivityManager connMgr, int type) {
        try {
            Network[] networks = connMgr.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connMgr.getNetworkInfo(mNetwork);
                if (networkInfo != null && networkInfo.getType() == type && networkInfo.isConnected()) {
                    return true;
                }
            }
            return false;
        }
        catch (Exception ignored) { return false; }
    }
}
