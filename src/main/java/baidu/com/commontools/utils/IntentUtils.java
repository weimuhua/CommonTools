package baidu.com.commontools.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class IntentUtils {

    public static boolean isActivityAvailable(@NonNull Context cxt, @NonNull Intent intent) {
        PackageManager manager = cxt.getPackageManager();
        return manager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Intent getJumpNotificationIntent(String packageName) {
        return new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                .putExtra(Settings.EXTRA_APP_PACKAGE, packageName);
    }
}
