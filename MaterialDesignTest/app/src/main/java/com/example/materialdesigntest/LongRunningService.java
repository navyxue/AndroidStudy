package com.example.materialdesigntest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class LongRunningService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            // 这里执行具体的逻辑操作
        }).start();
        MyListener listener = (a, b) -> {
            String result = a + b;
            return result;
        };
        // 获取AlarmManager的实例
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // 定义任务的触发时间
        int anHour = 60 * 60 * 1000; // 这是1小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        // 指定处理定时任务的服务为LongRunningService，
        Intent i = new Intent(this, LongRunningService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        // 由于Android在耗电性方面进行的优化，通过manage.set()方法开启的定时任务可能不准，如果特别要求Alarm准确执行，可以使用setExact()方法。
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        // 在使用Doze模式下，可以通过以下方法来开启定时任务，这样Alarm亦会准确的执行。
        //manager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        //manager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }
}
