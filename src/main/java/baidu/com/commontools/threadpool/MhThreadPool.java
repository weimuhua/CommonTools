package baidu.com.commontools.threadpool;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MhThreadPool {

    private static class SingletonHolder {
        private static final MhThreadPool sInstance = new MhThreadPool();
    }

    private static class PoolThreadFactory implements ThreadFactory {

        private boolean mUiTask;
        private AtomicInteger mCount = new AtomicInteger(1);

        public PoolThreadFactory(boolean uiTask) {
            mUiTask = uiTask;
        }

        @Override
        public Thread newThread(Runnable r) {
            if (mUiTask) {
                return new Thread(r, "MhUiThreadPool#" + mCount.getAndIncrement());
            } else {
                return new Thread(r, "MhBkhThreadPool#" + mCount.getAndIncrement());
            }
        }
    }

    private ThreadPoolExecutor mUiThreadPoolExecutor;
    private ThreadPoolExecutor mBkgThreadPoolExecutor;

    public static MhThreadPool getInstance() {
        return SingletonHolder.sInstance;
    }

    private MhThreadPool() {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        int uiThreadSize = Math.max(2, cpuCores / 2);
        int bkgThreadSize = 1;

        mUiThreadPoolExecutor = new ThreadPoolExecutor(uiThreadSize, uiThreadSize, 1, TimeUnit.SECONDS,
                new PriorityBlockingQueue<Runnable>(), new PoolThreadFactory(true));
        mBkgThreadPoolExecutor = new ThreadPoolExecutor(bkgThreadSize, bkgThreadSize, 1, TimeUnit.SECONDS,
                new PriorityBlockingQueue<Runnable>(), new PoolThreadFactory(false));
    }

    public void addUiTask(Runnable r) {
        ThreadPoolTask task = new ThreadPoolTask(r, System.currentTimeMillis());
        mUiThreadPoolExecutor.execute(task);
    }

    public void addUiTask(Runnable r, int priority) {
        ThreadPoolTask task = new ThreadPoolTask(r, priority, System.currentTimeMillis());
        mUiThreadPoolExecutor.execute(task);
    }

    public void addBkgTask(Runnable r) {
        ThreadPoolTask task = new ThreadPoolTask(r, System.currentTimeMillis());
        mBkgThreadPoolExecutor.execute(task);
    }

    public void addBkgTask(Runnable r, int priority) {
        ThreadPoolTask task = new ThreadPoolTask(r, priority, System.currentTimeMillis());
        mBkgThreadPoolExecutor.execute(task);
    }
}
