package baidu.com.commontools.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephonyUtils {

    public static String getPhoneNumber(Context cxt) {
        TelephonyManager tm = (TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }
}
