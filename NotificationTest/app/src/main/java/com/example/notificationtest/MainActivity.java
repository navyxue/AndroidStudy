package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice :
                sendNotice();
                break;
                default:
                    break;
        }
    }

    /**
     * 发送通知
     * */
    private void sendNotice() {
        // 让通知显示出来需要使用用PendingIntent
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pi = PendingIntent.getActivities(this, 0, new Intent[] {intent}, 0);
        NotificationManager manager = (NotificationManager) getSystemService
                (NOTIFICATION_SERVICE);

        //创建大文本样式
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("IG牛逼")
                .setSummaryText("哈哈哈哈哈")
                .bigText("回到通知栏上也是一样，每个开发者都只想着尽可能地去宣传自己的App，最后用户的手机就乱得跟鸡窝一样了。但是通知栏又还是有用处的，比如我们收到微信、短信等消息的时候，确实需要通知栏给我们提醒。因此分析下来，通知栏目前最大的问题就是，无法让用户对！");

        //NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.big));

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android.")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_launcher))
                // 设置通知发送后手机发出声音，PS：实机测试无效
                //.setSound(Uri.fromFile(new File("/system/media/audio/ringtones/AmigoTune.ogg")))
                // 设置PendingIntent
                .setContentIntent(pi)
                // 将系统通知的图标取消显示
                .setAutoCancel(true)
                // 设置通知发送后使手机振动，PS：实机测试无效
                //.setVibrate(new long[] {0, 1000, 1000, 1000})
                // 控制手机LED灯的显示
                //.setLights(Color.GREEN, 1000, 1000)
                // 使用通知默认效果，它会根据当前手机的环境来决定播放什么铃声，以及如何振动
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                // 允许构建富文本的通知内容，可以有图标和文字，还可以有长文字和图片
                //.setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))
                // 设置显示图片
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_launcher)))
                // 设置优先级
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        // 通知管理器唤醒通知，参数“1”为通知的唯一ID
        manager.notify(1, notification);
    }

    private void sendNoticeV2() {
        /*NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("This is content title");
        builder.setContentText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android.");
        builder.setSubText("I'm SubText");
        builder.setTicker("通知到达");
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher));
        builder.setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pi = PendingIntent.getActivities(this, 0, new Intent[] {intent}, 0);

        builder.setContentIntent(pi);

        manager.notify(1, builder.build());*/
    }

    private void sendNoticeV3() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "subscribe")
                //.setCustomContentView(remoteView)
                .setContentTitle("IG牛逼")
                .setContentText("回到通知栏上也是一样，每个开发者都只想着尽可能地去宣传自己的App，最后用户的手机就乱得跟鸡窝一样了。但")
                // .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                // .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true);
        //创建大文本样式
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("IG牛逼")
                .setSummaryText("哈哈哈哈哈")
                .bigText("回到通知栏上也是一样，每个开发者都只想着尽可能地去宣传自己的App，最后用户的手机就乱得跟鸡窝一样了。但是通知栏又还是有用处的，比如我们收到微信、短信等消息的时候，确实需要通知栏给我们提醒。因此分析下来，通知栏目前最大的问题就是，无法让用户对！");

        builder.setStyle(bigTextStyle); //设置大文本样式

        Notification notification = builder.build();
        manager.notify(1, notification);
    }
}
