package com.example.bxh.sayhello;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.example.bxh.sayhello.inject.InjectUtils;
import com.example.bxh.sayhello.inject.ViewInject;
import com.example.bxh.sayhello.widgets.Line;
import com.example.bxh.sayhello.widgets.LineBuilder;
import com.example.bxh.sayhello.widgets.TableView;

public class TableActivity extends Activity {
    private static final String TAG = "TableActivity";
    @ViewInject(value = R.id.table, value3 = 0)
    private TableView mTableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        InjectUtils.autoInjectAllField(this);
        float[] vals = {1f, 1.5f, 3f, 3.5f, 2f, 2.5f,3.0f,4.5f,3.5f,4f,5f,6f};
        float[] vals1 = {5f, 6.5f, 8f, 11.5f, 7f, 7.5f,8.0f,9.5f,8.5f,9f,10f,11f};
        Line line = new LineBuilder().setValues(vals).build();
        LineBuilder builder1 = new LineBuilder();
        builder1.getDefaultConnectionPaint().setColor(Color.RED);
        builder1.getDefaultNodePaint().setColor(Color.RED);
        builder1.getDefaultNodeTextPaint().setColor(Color.RED);
        Line line1 = builder1.setValues(vals1).build();
        mTableView.setmGuardLineVal(1.5f);
        mTableView.addLine(line);
        mTableView.addLine(line1);


        Tree.test();
    }


}
