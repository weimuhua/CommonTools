package baidu.com.commontools.utils;

import android.graphics.BitmapFactory;

public class BitmapUtils {

    /**
     * Check if the photo file is corrupted
     * @param filePath the photo file path
     */
    public static boolean isImgCorrupted(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(filePath, options);
        return options.mCancel || options.outWidth == -1 || options.outHeight == -1;
    }
}
