package com.bxh.gradleapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import butterknife.BindView;

/**
 * Created by bxh on 2/22/17.
 */

public class TestActivity2 extends Activity {
    @BindView(R.id.text)
    TextView mTextView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        mTextView1.setText("--TestActivity2--");
        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity2.this,TestActivity.class));
            }
        });
    }
}
