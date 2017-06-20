package com.example.bxh.sayhello.command;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.bxh.sayhello.R;

/**
 * Created by buxiaohui on 6/20/17.
 */

public class CommandActivity extends Activity implements View.OnClickListener {
    static String TAG = "CommandActivity";
    private DrawSurfaceView mDrawSurfaceView;
    private DrawPathCommand mDrawPathCommand;
    private Paint mPaint;
    private IBrush mIBrush;//与命令模式无关
    private Button btRedo, btUndo, btBlue, btNormal, btCircle;

    public static void toCommandActivity(Activity activity) {
        if (activity != null) {
            activity.startActivity(new Intent(activity, CommandActivity.class));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal:
                mIBrush = new NormalBrush();
                break;
            case R.id.circle:
                mIBrush = new CircleBrush();
                break;
            case R.id.blue:
                mPaint = new Paint();
                mPaint.setColor(0xff0000ff);
                mPaint.setStrokeWidth(3);
                break;
            case R.id.redo:
                mDrawSurfaceView.redo();
                if (!mDrawSurfaceView.canRedo()) {
                    btRedo.setEnabled(false);
                }
                btUndo.setEnabled(true);
                break;
            case R.id.undo:
                mDrawSurfaceView.undo();
                if (!mDrawSurfaceView.canUndo()) {
                    btUndo.setEnabled(false);
                }

                btRedo.setEnabled(true);
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        btRedo = (Button) findViewById(R.id.redo);
        btCircle = (Button) findViewById(R.id.circle);
        btNormal = (Button) findViewById(R.id.normal);
        btBlue = (Button) findViewById(R.id.blue);
        btUndo = (Button) findViewById(R.id.undo);
        btRedo.setEnabled(false);
        btUndo.setEnabled(false);
        btRedo.setOnClickListener(this);
        btUndo.setOnClickListener(this);
        btBlue.setOnClickListener(this);
        btCircle.setOnClickListener(this);
        btNormal.setOnClickListener(this);

        mPaint = new Paint();
        mPaint.setColor(0xFFFFFFFF);
        mPaint.setStrokeWidth(3);

        mIBrush = new NormalBrush();

        LinearLayout viewGroup = (LinearLayout) findViewById(R.id.activity_command);
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = viewGroup.getChildAt(i);
            Log.i(TAG, "v=" + v);
        }
        mDrawSurfaceView = (DrawSurfaceView) findViewById(R.id.surface);
        Log.i(TAG, "mDrawSurfaceView=" + mDrawSurfaceView);
        mDrawSurfaceView.setOnTouchListener(new DrawTouchListener());

    }

    private class DrawTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mDrawPathCommand = new DrawPathCommand();
                mDrawPathCommand.paint = mPaint;
                mDrawPathCommand.path = new Path();
                mIBrush.down(mDrawPathCommand.path, event.getX(), event.getY());
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                mIBrush.move(mDrawPathCommand.path, event.getX(), event.getY());
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                mIBrush.up(mDrawPathCommand.path, event.getX(), event.getY());
                mDrawSurfaceView.add(mDrawPathCommand);
                mDrawSurfaceView.isDrawing = true;
                btRedo.setEnabled(false);
                btUndo.setEnabled(true);
            }
            return true;
        }
    }

}
