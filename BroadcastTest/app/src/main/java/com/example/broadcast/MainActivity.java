package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        // 设置要监听的广播，这里为网络状态发生改变的广播
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        // 创建广播接收器的类
        networkChangeReceiver = new NetworkChangeReceiver();
        // 向系统注册这个广播接收器，以及其要接受广播的内容
        registerReceiver(networkChangeReceiver, intentFilter);

        // 发送自定义广播
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用Intent进行传递自定义的广播
                Intent intent = new Intent("com.example.broadcast.MY_BROADCAST");
                // 发送该广播
                sendOrderedBroadcast(intent, null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在活动中将动态注册的广播进行取消
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
