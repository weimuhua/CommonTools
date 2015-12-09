package baidu.com.commontools.utils;


import android.os.Handler;
import android.os.HandlerThread;

public class AsyncHandler {

    private static class SingletonHolder {
        private static AsyncHandler sInstance = new AsyncHandler();
    }

    private Handler mHandler;

    public static AsyncHandler getInstance() {
        return SingletonHolder.sInstance;
    }

    public AsyncHandler() {
        HandlerThread handlerThread = new HandlerThread("AsyncHandler");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
    }

    public void post(Runnable r) {
        mHandler.post(r);
    }

    public void postDelayed(Runnable r, long delayMillis) {
        mHandler.postDelayed(r, delayMillis);
    }
}
