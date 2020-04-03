package baidu.com.commontools.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import androidx.annotation.Nullable;

import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by Wayne on 2017/3/26.
 */

public class ProcessUtils {

    @Nullable
    public static String getProcessName(Context cxt) {
        int pid = Process.myPid();
        ActivityManager am = (ActivityManager) cxt.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null && !runningApps.isEmpty()) {
            for (ActivityManager.RunningAppProcessInfo info : runningApps) {
                if (info.pid == pid) return info.processName;
            }
        }

        return null;
    }
}
