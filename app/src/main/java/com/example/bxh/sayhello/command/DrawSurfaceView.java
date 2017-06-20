package com.example.bxh.sayhello.command;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by buxiaohui on 6/20/17.
 */

public class DrawSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public boolean isDrawing;
    public boolean isRunning;
    private Bitmap mBitmap;
    private DrawInvoker mInvoker;//命令模式中的invoker,持有command
    private DrawThread mThread;

    public DrawSurfaceView(Context context) {
        super(context);
    }

    public DrawSurfaceView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        mInvoker = new DrawInvoker();
        mThread = new DrawThread();
        getHolder().addCallback(this);
    }

    public void add(DrawPathCommand command) {
        mInvoker.add(command);
    }

    public void undo() {
        isDrawing = true;
        mInvoker.undo();
    }

    public void redo() {
        isDrawing = true;
        mInvoker.redo();
    }

    public boolean canRedo() {
        return mInvoker.canRedo();
    }

    public boolean canUndo() {
        return mInvoker.canUndo();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
         mBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
      boolean retry = true;
        isRunning = false;
        while (retry){
            try {
                mThread.join();
                retry = false;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class DrawThread extends Thread {
        @Override
        public void run() {
            Canvas canvas = null;
            while (isRunning){
                if(isDrawing){
                    try {
                        canvas = getHolder().lockCanvas(null);
                        if(mBitmap == null){
                            mBitmap = Bitmap.createBitmap(1,1, Bitmap.Config.RGB_565);
                        }
                        Canvas c = new Canvas(mBitmap);
                        c.drawColor(0, PorterDuff.Mode.CLEAR);
                        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                        mInvoker.excute(c);
                        canvas.drawBitmap(mBitmap,0,0,null);
                    }catch (Exception e){

                    }finally {
                        getHolder().unlockCanvasAndPost(canvas);
                    }
                    isDrawing = false;
                }
            }
        }
    }
}
