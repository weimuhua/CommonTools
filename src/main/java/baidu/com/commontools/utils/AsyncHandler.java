package baidu.com.commontools.utils;


import android.os.Handler;
import android.os.HandlerThread;

public class AsyncHandler {

    private static class SingletonHolder {
        private static AsyncHandler sInstance = new AsyncHandler();
    }

    private Handler mHandler;
    private HandlerThread mThread;

    public static AsyncHandler getInstance() {
        return SingletonHolder.sInstance;
    }

    public AsyncHandler() {
        mThread = new HandlerThread("AsyncHandler");
        mThread.start();
        mHandler = new Handler(mThread.getLooper());
    }

    public void post(Runnable r) {
        mHandler.post(r);
    }

    public void postDelayed(Runnable r, long delayMillis) {
        mHandler.postDelayed(r, delayMillis);
    }
}
