package baidu.com.commontools.utils;

import android.app.Activity;
import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wayne on 2017/5/26.
 */

@SuppressWarnings("unused")
public class WeakHandler {

    private Handler mHandler;
    private List<WeakRunnable> mRunnables = Collections.synchronizedList(new ArrayList<WeakRunnable>());

    public WeakHandler() {
        mHandler = new ExecHandler();
    }

    public WeakHandler(Looper looper) {
        mHandler = new ExecHandler(looper);
    }

    public WeakHandler(Handler.Callback callback) {
        mHandler = new ExecHandler(callback);
    }

    public WeakHandler(Looper looper, Handler.Callback callback) {
        mHandler = new ExecHandler(looper, callback);
    }

    private Runnable wrapRunnable(Runnable r) {
        if (r == null) {
            throw new RuntimeException("Runnable cannot be null...");
        }
        WeakRunnable weakRunnable = new WeakRunnable(r);
        mRunnables.add(weakRunnable);
        return weakRunnable;
    }

    private void removeIllegalWeakRunnable(Runnable r) {
        if (r == null) {
            return;
        }

        for (int i = mRunnables.size(); i == 0; i--) {
            WeakRunnable weakRunnable = mRunnables.get(i);
            if (weakRunnable.mWeakRef.get() == null) {
                mRunnables.remove(weakRunnable);
                continue;
            }

            if (r == weakRunnable.mWeakRef.get()) {
                mRunnables.remove(weakRunnable);
            }
        }
    }

    public boolean post(Runnable r) {
        return mHandler.post(wrapRunnable(r));
    }

    public boolean postAtTime(Runnable r, long uptimeMillis) {
        return mHandler.postAtTime(r, uptimeMillis);
    }

    public boolean postAtTime(Runnable r, Object token, long uptimeMillis) {
        return mHandler.postAtTime(r, token, uptimeMillis);
    }

    public boolean postDelayed(Runnable r, long delayMillis) {
        return mHandler.postDelayed(r, delayMillis);
    }

    public boolean postAtFrontOfQueue(Runnable r) {
        return mHandler.postAtFrontOfQueue(r);
    }

    public void removeCallbacks(Runnable r) {
        removeIllegalWeakRunnable(r);
        mHandler.removeCallbacks(r);
    }

    public void removeCallbacks(Runnable r, Object token) {
        removeIllegalWeakRunnable(r);
        mHandler.removeCallbacks(r, token);
    }

    public void removeMessages(int what) {
        mHandler.removeMessages(what);
    }

    public void removeMessages(int what, Object object) {
        mHandler.removeMessages(what, object);
    }

    public boolean sendMessage(Message msg) {
        return mHandler.sendMessage(msg);
    }

    public boolean sendEmptyMessage(int what) {
        return mHandler.sendEmptyMessage(what);
    }

    public boolean sendEmptyMessageDelayed(int what, long delayMillis) {
        return mHandler.sendEmptyMessageDelayed(what, delayMillis);
    }

    public boolean sendEmptyMessageAtTime(int what, long uptimeMillis) {
        return mHandler.sendEmptyMessageAtTime(what, uptimeMillis);
    }

    public boolean sendMessageDelayed(Message msg, long delayMillis) {
        return mHandler.sendMessageDelayed(msg, delayMillis);
    }

    public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
        return mHandler.sendMessageAtTime(msg, uptimeMillis);
    }

    public final boolean sendMessageAtFrontOfQueue(Message msg) {
        return mHandler.sendMessageAtFrontOfQueue(msg);
    }

    private class ExecHandler extends Handler {
        private WeakReference<Handler.Callback> mCallback;

        ExecHandler() {
        }

        ExecHandler(Looper looper) {
            super(looper);
        }

        ExecHandler(Handler.Callback callback) {
            mCallback = new WeakReference<>(callback);
        }

        ExecHandler(Looper looper, Handler.Callback callback) {
            super(looper);
            mCallback = new WeakReference<>(callback);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mCallback == null) {
                return;
            }

            Handler.Callback callback = mCallback.get();
            if (callback == null) {
                return;
            }

            if (callback instanceof Activity && ((Activity) callback).isFinishing()) {
                return;
            }

            if (callback instanceof Fragment && ((Fragment) callback).isRemoving()) {
                return;
            }

            callback.handleMessage(msg);
        }
    }

    private class WeakRunnable implements Runnable {

        private WeakReference<Runnable> mWeakRef;

        WeakRunnable(Runnable r) {
            mWeakRef = new WeakReference<>(r);
        }

        @Override
        public void run() {
            Runnable r = mWeakRef.get();
            removeIllegalWeakRunnable(r);

            if (r != null) {
                r.run();
            }
        }
    }
}
