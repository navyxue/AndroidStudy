package com.example.activitytest.activitytest;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动管理器
 */
public class ActivityCollector {
    // 活动集合
    private static List<Activity> activities = new ArrayList<>();

    /**
     * 添加一个活动
     * @param activity 活动
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 删除一个活动
     * @param activity 活动
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 将List中的所有活动全部销毁掉
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        // 杀掉当前程序进程
        // killProcess()方法用于杀掉一个进程，它接受一个进程ID
        // killProcess()方法只能杀掉当前程序的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
