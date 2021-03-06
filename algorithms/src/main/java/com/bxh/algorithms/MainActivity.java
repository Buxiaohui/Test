package com.bxh.algorithms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.bxh.algorithms.OtherAlgorithms.BFS;
import com.bxh.algorithms.OtherAlgorithms.Cell;
import com.bxh.algorithms.OtherAlgorithms.Dijkstra;
import com.bxh.algorithms.OtherAlgorithms.FindSameElements;
import com.bxh.algorithms.OtherAlgorithms.StringTest;
import com.bxh.algorithms.OtherAlgorithms.TestAlgorithms;
import com.bxh.algorithms.dynamic.ThiefTest;
import com.bxh.algorithms.leetcode.FindMedinaIn2Array;
import com.bxh.algorithms.leetcode.LeetCode2;
import com.bxh.algorithms.leetcode.LeetCode3;
import com.bxh.algorithms.node.NodeAlgorithms;
import com.bxh.algorithms.sort.HeapSort;
import com.bxh.algorithms.sort.QuickSort;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //HeapSort.sortTest();
        //StringTest.test();
        //Cell.getCellCount();
        //new BFS().test();
        //Dijkstra.main();
        //FindSameElements.find();
        //QuickSort.test();
        //NodeAlgorithms.testNodes();
        //StringTest.test();
        //ThiefTest.test();
        //FindMedinaIn2Array.test();
        //LeetCode2.test();
        //LeetCode3.test();
        TestAlgorithms.findlog2NTest();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
