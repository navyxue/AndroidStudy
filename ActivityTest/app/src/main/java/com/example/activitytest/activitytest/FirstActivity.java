package com.example.activitytest.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 每个活动都重写了这个方法，它会在活动第一次被创建的时候调用。你应该在这个方法中完成活动的初始化操作，比如说加载布局、绑定事件等。
        Log.d("d-FirstActivity", "Task id is " + getTaskId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FirstActivity.this, "You clicked Button 1", Toast.LENGTH_LONG).show();
                // 显式Intent
                // Intent构造方法接收两个参数，第一个参数Contxt要求提供一个启动活动的上下文，第二个参数Class则是指定想要启动的目标活动
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                // 用于开启活动，接收一个Intent参数
//                startActivity(intent);

                // 隐式Intent
                // 每个Intent中只能指定一个Action，但却能指定多个category
                //Intent intent = new Intent("com.example.activitytest.ACTION_START");
                // 调用addCategory()方法来添加一个category
                //intent.addCategory("com.example.activitytest.MY_CATEGORY");
                //startActivity(intent);

                // 隐式打开浏览器
                // Intent.ACTION_VIEW为Android系统内置的动作，其常量值为android.intent.action.VIEW
//                Intent intent = new Intent(Intent.ACTION_VIEW);
                //Uri.parse()将一个网址字符串解析成一个Uri对象，主要用于指定当前Intent正在操作的数据
//                intent.setData(Uri.parse("https://www.baidu.com"));
//                startActivity(intent);
                // 隐式打开电话
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:10086"));
//                startActivity(intent);

                // 使用Intent在活动之间传递数据
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                String data = "Hello SecondActivity";
                // 将参数保存在Intent中，putExtra有多个方法重载
                // 第一个参数是key，第二个参数是要传递的数据
//                intent.putExtra("extra_data", data);
                // 执行Intent
//                startActivity(intent);
                // 此方法亦可启动一个活动，并在活动销毁时能够返回一个结果给上一个活动
                // 第一个参数为Intent，第二个参数是请求码，用于在之后的回调中判断数据来源
//                startActivityForResult(intent, 1);

                // 测试standard模式-栈顶
//                Intent intent = new Intent(FirstActivity.this, FirstActivity.class);
//                startActivity(intent);
                // 测试standard模式-非栈顶
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                startActivity(intent);
                // 调用SenondActivity封装好的启动活动的方法
                SecondActivity.actionStart(FirstActivity.this, "data1", "data2");
            }
        });

        Button button2 = (Button) findViewById(R.id.button1_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 销毁活动
                finish();
            }
        });
    }

    /**
     * 重写活动的菜单创建方法
     * @param menu 菜单对象
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 获取MenuInflater对象，调用inflate方法给当前活动创建菜单
        // 第一个参数为定义菜单项的资源文件
        // 第二个参数为将此菜单项添加到哪个Menu对象中
        getMenuInflater().inflate(R.menu.main, menu);
        // 返回true，否则创建的菜单不会显示出来
        return true;
        // return super.onCreateOptionsMenu(menu);
    }

    /**
     * 重写菜单项的响应事件
     * @param item 菜单项
     * @return 返回true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
                default:
        }
        return true;
        //return super.onOptionsItemSelected(item);
    }

    /**
     * 窗口被销毁时回调方法
     * @param requestCode 请求码，启动活动时传入的请求码
     * @param resultCode 处理结果，返回数据时传入的处理结果
     * @param data 携带数据的Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 首先通过检查requestCode的值来判断数据来源，确定数据来源之后，
         * 再通过resultCode的值来判断处理结果是否成功，最后data中取出数据
         */
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnData = data.getStringExtra("data_return");
                    Log.d("FirstActivity", returnData);
                }
                break;
                default:
        }
    }

    @Override
    protected void onStart() {
        // 这个方法在活动由不可见变为可见的时候调用。
        Log.d("d-FirstActivity", "FirstActivity.onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        // 这个方法在活动准备好和用户进行交互的时候调用。此时的活动一定位于栈的栈顶，并且处于运行状态。
        Log.d("d-FirstActivity", "FirstActivity.onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        // 这个方法在系统准备去启动或者恢复另一个活动的时候调用。我们通常会在这个方法中将一些消耗CPU的资源释放掉，以及保存一些关键数据，但这个方法的执行速度一定要快，不然会影响到新的栈顶活动的使用。
        Log.d("d-FirstActivity", "FirstActivity.onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        // 这个方法在活动完全不可见的时候调用。它和onPause()方法的主要区别在于，如果启动的新活动是一个对话框式的活动，那么onPause()方法会得到执行，而onStop()方法并不会执行。
        Log.d("d-FirstActivity", "FirstActivity.onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // 这个方法在活动被销毁之前调用，之后活动的状态将变为销毁状态。
        Log.d("d-FirstActivity", "FirstActivity.onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        // 这个方法在活动由停止状态变为运行状态之前调用，也就是活动被重新启动了。
        Log.d("d-FirstActivity", "FirstActivity.onRestart");
        super.onRestart();
    }
}
