package baidu.com.commontools.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    private static ConnectivityManager sCM;

    private static ConnectivityManager getConnectiveManager(Context cxt) {
        if (sCM == null) {
            Context appCxt = cxt.getApplicationContext();
            sCM = (ConnectivityManager) appCxt.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return sCM;
    }

    public boolean isNetworkAvailable(Context cxt) {
        ConnectivityManager cm = getConnectiveManager(cxt);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
