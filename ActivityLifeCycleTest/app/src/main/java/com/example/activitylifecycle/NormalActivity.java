package com.example.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;

public class NormalActivity extends AppCompatActivity {

    private static final String TAG = "d-NormalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);

        // 得到Intent对象
        Intent intent = getIntent();
        // 从Intent中取出Bundle对象
        Bundle bundle = intent.getBundleExtra("extra_key");
        Serializable serializable = bundle.getSerializable("extra_person");
        if (serializable instanceof Person) {
            Person person = (Person) serializable;
            Log.d(TAG, person.toString());
        }
    }
}
