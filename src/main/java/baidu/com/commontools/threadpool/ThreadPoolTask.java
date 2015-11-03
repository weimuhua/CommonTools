package baidu.com.commontools.threadpool;

import android.os.Process;
import android.support.annotation.Nullable;


public class ThreadPoolTask implements Runnable, Comparable<ThreadPoolTask> {

    public static final int PRIORITY_LOW = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_HIGH = 3;

    private Runnable mRunnable;
    private int mPriority;
    private long mQueueTime;

    public ThreadPoolTask(Runnable r, long queueTime) {
        mRunnable = r;
        mPriority = PRIORITY_NORMAL;
        mQueueTime = queueTime;
    }

    public ThreadPoolTask(Runnable r, int priority, long queueTime) {
        mRunnable = r;
        mPriority = priority;
        mQueueTime = queueTime;
    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        mRunnable.run();
        mRunnable = null;
    }

    @Override
    public int compareTo(@Nullable ThreadPoolTask another) {
        if (another == null) return -1;
        if (mPriority > another.mPriority) {
            return -1;
        } else {
            if (mQueueTime < another.mQueueTime) {
                return -1;
            } else if (mQueueTime > another.mQueueTime) {
                return 1;
            }
            return 0;
        }
    }
}
