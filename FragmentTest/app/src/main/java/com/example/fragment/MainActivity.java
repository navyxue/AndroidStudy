package com.example.fragment;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "d-MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        Button button_click = (Button) findViewById(R.id.button_click);
        button_click.setOnClickListener(this);

        replaceFragment(new AnotherRightFragment());

        Log.d(TAG, "onCreate: " + Resources.getSystem().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                /*if (getSupportFragmentManager().findFragmentById(R.id.right_layout) != null) {
                    if (getSupportFragmentManager().findFragmentById(R.id.right_layout) instanceof RightFragment) {
                        replaceFragment(new AnotherRightFragment());
                    } else if (getSupportFragmentManager().findFragmentById(R.id.right_layout) instanceof AnotherRightFragment) {
                        replaceFragment((new RightFragment()));
                    }
                }*/
                break;
            case R.id.button_click:
                /*if (getSupportFragmentManager().findFragmentById(R.id.right_layout) != null) {
                    if (getSupportFragmentManager().findFragmentById(R.id.right_layout) instanceof RightFragment) {
                        TextView textView = (TextView) getSupportFragmentManager().findFragmentById(R.id.right_layout).getView().findViewById(R.id.right_text);
                        textView.setText("RightFragment Hello World!");
                    } else if (getSupportFragmentManager().findFragmentById(R.id.right_layout) instanceof AnotherRightFragment) {
                        TextView textView = (TextView) getSupportFragmentManager().findFragmentById(R.id.right_layout).getView().findViewById(R.id.anotherFragment_text);
                        textView.setText("AnotherFragment Hello World!");
                    }
                }*/
                break;
                default:
                    break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
    }
}
