package com.example.bxh.sayhello;

import android.app.Activity;
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
        float[] vals = {1f, 1.5f, 3f, 6f, 2f, 5f,7f,19f,17f,16f,12f,10f};
        Line line = new LineBuilder().setValues(vals).build();
        mTableView.addLine(line);
    }


}
