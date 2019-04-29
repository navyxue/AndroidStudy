package com.example.activitytest.activitytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("d-SecondActivity", "Task id is " + getTaskId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        // 获取Intent中传递过来的数据
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra_data");
//        Log.d("SecondActivity", data);

        Button button2 = (Button) findViewById(R.id.button_2);
        /*button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent();
                intent1.putExtra("data_return", "Hello FirstActivity");
                // 设置返回的数据
                // 第一个参数为处理结果，一般用RESULT_OK或RESULT_CANCELED这两个值
                // 第二个参数带有数据的Intent传递回去
                setResult(RESULT_OK, intent1);
                // 销毁活动
                finish();
                //Toast.makeText(SecondActivity.this, "我是第二个Activity", Toast.LENGTH_SHORT).show();
            }
        });*/
        // 测试standard模式-非栈顶
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
//                startActivity(intent);
//            }
//        });
        // 测试singleInstance启动模式
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 封装启动一个活动的方法，告知启用本活动时需要哪些参数
     * @param context
     * @param data1
     * @param data2
     */
    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }

    /**
     * 重写按下Back键的方法
     */
    @Override
    public void onBackPressed() {
        // 新建一个活动
        Intent intent = new Intent();
        // 设置返回的数据
        intent.putExtra("data_return", "Hello FirstActivity!");
        // 给上一个活动返回结果
        setResult(RESULT_OK, intent);
        // 结束活动
        finish();
    }

    @Override
    protected void onStart() {
        Log.d("d-SecondActivity", "SecondActivity.onStart");
        super.onStart();
    }

    @Override
    protected void onPostResume() {
        Log.d("d-SecondActivity", "SecondActivity.onResume");
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        Log.d("d-SecondActivity", "SecondActivity.onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("d-SecondActivity", "SecondActivity.onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("d-SecondActivity", "SecondActivity.onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("d-SecondActivity", "SecondActivity.onRestart");
        super.onRestart();
    }
}
