package com.example.activitytest;

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
    private static final String TAG = "FirstActivity";
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        Button button1 = (Button)findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class); // 显式intent
                String data = "Hello SecondActivity";
                intent.putExtra("extra_data", data); // intent可以传递数据至其他活动

                // 由被被启动的活动方来提供静态方法，会是更佳方式
                // SecondActivity.actionStart(FirstActivity.this, "data");

                //Intent intent = new Intent("com.example.activitytest.ACTION_START"); // 隐式intent
                //intent.addCategory("com.example.activitytest.MY_CATEGORY");

                //Intent intent = new Intent(Intent.ACTION_VIEW); // 隐式intent android内置action
                //intent.setData(Uri.parse("http://www.baidu.com"));

                //Intent intent = new Intent(Intent.ACTION_DIAL); // 隐式intent android内置action
                //intent.setData(Uri.parse("tel:10086"));
                //startActivity(intent);

                startActivityForResult(intent, 1); // 接受返回值 第二个参数需要是个唯一值
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d(TAG, returnedData);
                }
                break;
            default:
        }
    }
}
