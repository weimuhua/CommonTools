package baidu.com.commontools.ui;

import android.app.Activity;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;


public class BaseActivity extends Activity {

    private static final WeakHashMap<String, WeakReference<BaseActivity>> sInstanceList
            = new WeakHashMap<>();

    private String mActivityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseActivity instance = getExistInstance();
        if (instance != null) {
            instance.finish();
        }

        synchronized (sInstanceList) {
            sInstanceList.put(mActivityName, new WeakReference<>(this));
        }
    }

    private BaseActivity getExistInstance() {
        if (null == mActivityName) {
            mActivityName = getClass().getName();
        }
        WeakReference<BaseActivity> refs = sInstanceList.get(mActivityName);
        return refs == null ? null : refs.get();
    }
}
