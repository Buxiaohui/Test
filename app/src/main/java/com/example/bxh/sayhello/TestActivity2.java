package com.example.bxh.sayhello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bxh.sayhello.inject.InjectUtils;
import com.example.bxh.sayhello.inject.ViewInject;

/**
 * Created by bxh on 2/22/17.
 */

public class TestActivity2 extends Activity {
    @ViewInject(value = R.id.text1, value3 = 0)
    private TextView mTextView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtils.autoInjectAllField(this);
        mTextView1.setText("--TestActivity2--");
        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity2.this,TestActivity.class));
            }
        });
    }
}
