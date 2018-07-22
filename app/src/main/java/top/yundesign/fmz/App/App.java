package top.yundesign.fmz.App;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import org.xutils.DbManager;
import org.xutils.x;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import top.yundesign.fmz.UI.activity.MainActivity;
import top.yundesign.fmz.config.AppConfig;
import top.yundesign.fmz.utils.LogUtils;
import top.yundesign.fmz.utils.SpUtils;

public class App extends Application {
    @Inject
    ExecutorService mCachedThreadPool;
    @Inject
    DbManager db;
    @Inject
    SpUtils mSp;

    public static App app;
    private LinkedList<Activity> allActivities;
    private AppComponent appComponent;
    private String TAG=getClass().getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        InitApp();
    }

    public AppComponent Component() {
        return appComponent;
    }

    public DbManager getDb() {
        return db;
    }

    public SpUtils getmSp() {
        return mSp;
    }

    private void InitApp() {
        allActivities=new LinkedList<>();
        x.Ext.init(this);
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
        appComponent.inject(this);
        registerActivitysCallbacks();
    }

    public void finishActivity(Class<? extends Activity> clazz) {
        if (clazz != null) {
            Iterator<Activity> iter = allActivities.iterator();
            while (iter.hasNext()) {
                Activity activity = iter.next();
                if (clazz.getSimpleName().equals(activity.getClass().getSimpleName())) {
                    activity.finish();
                    break;
                }
            }
        }
    }

    /**
     * 判定是否存在某个Activity
     *
     * @param clazz
     * @return
     */
    public Boolean hasActivity(Class<? extends Activity> clazz) {
        //判断某一个类是否存在任务栈里面
        Intent intent = new Intent(this, clazz);
        ComponentName cmpName = intent.resolveActivity(getPackageManager());
        boolean flag = false;
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);  //获取从栈顶开始往下查找的10个activity
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }

    /**
     * 获取Activity数量
     *
     * @return
     */
    public int getActivityCount() {
        return allActivities.size();
    }

    /**
     * 销毁除欢迎界面以外的其他界面；
     */
    public void finishAllActivitys() {
        LogUtils.v(TAG, "开始销毁所有界面！");
        Iterator<Activity> iter = allActivities.iterator();
        while (iter.hasNext()) {
            Activity activity = iter.next();
            if (activity.getClass() != MainActivity.class) {
                activity.finish();
                LogUtils.v(TAG, "销毁：" + activity.getClass().getSimpleName());
            }
        }
    }

    private void registerActivitysCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                allActivities.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                LogUtils.d(AppConfig.MODULE_APP, activity.getClass().getSimpleName() + " onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                LogUtils.d(AppConfig.MODULE_APP, activity.getClass().getSimpleName() + " onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                LogUtils.d(AppConfig.MODULE_APP, activity.getClass().getSimpleName() + " onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                LogUtils.d(AppConfig.MODULE_APP, activity.getClass().getSimpleName() + " onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                LogUtils.d(AppConfig.MODULE_APP, activity.getClass().getSimpleName() + " onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                LogUtils.d(AppConfig.MODULE_APP, activity.getClass().getSimpleName() + " onActivityDestroyed");
                allActivities.remove(activity);
            }
        });
    }
}
