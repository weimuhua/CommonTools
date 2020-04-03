package baidu.com.commontools.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.Nullable;

public class NetworkUtils {

    public static final int NET_TYPE_NONE = 1;
    public static final int NET_TYPE_APN = 2;
    public static final int NET_TYPE_WIFI = 3;

    private static ConnectivityManager sCM;

    /**
     * get connectivityManager
     */
    @Nullable
    private static ConnectivityManager getConnectiveManager(Context cxt) {
        if (sCM == null) {
            Context appCxt = cxt.getApplicationContext();
            sCM = (ConnectivityManager) appCxt.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return sCM;
    }

    /**
     * check if the network is available
     */
    public boolean isNetworkConnected(Context cxt) {
        ConnectivityManager cm = getConnectiveManager(cxt);
        if (cm == null) return false;

        NetworkInfo info = cm.getActiveNetworkInfo();
        return info.isConnected();
    }

    /**
     * get network type
     */
    public static int getNetworkType(Context cxt) {
        ConnectivityManager cm = getConnectiveManager(cxt);
        if (cm == null) return NET_TYPE_NONE;

        NetworkInfo info = cm.getActiveNetworkInfo();
        int type = info.getType();
        int subType = info.getSubtype();
        if (type == ConnectivityManager.TYPE_WIFI
                || type == ConnectivityManager.TYPE_WIMAX
                || type == ConnectivityManager.TYPE_ETHERNET) {
            return NET_TYPE_WIFI;
        } else if (type == ConnectivityManager.TYPE_MOBILE
                || (type == ConnectivityManager.TYPE_BLUETOOTH && subType > 0)) {
            return NET_TYPE_APN;
        }
        return NET_TYPE_NONE;
    }
}
