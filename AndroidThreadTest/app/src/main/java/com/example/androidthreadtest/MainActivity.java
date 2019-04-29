package com.example.androidthreadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int UPDATE_TEXT = 1;
    private TextView textView;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    // 这里可以进行UI操作
                    textView.setText("Nice to meet you");
                    break;
                    default:
                        break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button changeText = (Button) findViewById(R.id.change_text);
        textView = (TextView) findViewById(R.id.text);
        changeText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_text:
                Message message = new Message();
                message.what = UPDATE_TEXT;
                handler.sendMessage(message); // 将Message对象发送出去
                break;
                default:
                    break;
        }
    }
}
