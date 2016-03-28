package baidu.com.commontools.utils;


import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

public class StorageUtils {

    /**
     * Get the internal storage directory (ROM)
     */
    public static String getInternalStorageDirectory() {
        return Environment.getDataDirectory().getAbsolutePath();
    }

    /**
     * Get the available internal storage size (ROM).
     */
    @SuppressWarnings({"deprecation", "unused"})
    public static long getInternalStorageAvailableSize() {
        StatFs stat = new StatFs(getInternalStorageDirectory());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
        } else {
            return (long) stat.getBlockSize() * (long) stat.getAvailableBlocks();
        }
    }

    /**
     * Check if the external storage exists (SD Card)
     * @return true if the external storage exists, otherwise false
     */
    public static boolean isExternalStorageAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * Get the external storage directory (SD Card)
     */
    @SuppressWarnings("unused")
    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
