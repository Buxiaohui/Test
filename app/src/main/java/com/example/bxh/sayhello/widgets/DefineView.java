package com.example.bxh.sayhello.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.bxh.sayhello.R;

/**
 * Created by bxh on 5/17/17.
 */

public class DefineView extends View {
    PathMeasure pathMeasure;
    Path path;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    float value;
    Paint paint;
    Paint paint1;
    Bitmap bitmap;

    public DefineView(Context context) {
        super(context);
    }

    public DefineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        int centerX = 500;
        int centerY = 800;
        int radius = 200;
        path.addCircle(centerX, centerY, radius, Path.Direction.CW);//顺时针，逆时针
        canvas.drawPath(path, paint);
        pathMeasure = new PathMeasure(path, false);
        float[] pos = new float[2];
        float[] tan = new float[2];
        pathMeasure.getPosTan(pathMeasure.getLength() * value, pos, tan);
        Log.d("BBBBB", "value=" + value);
        //canvas.drawCircle(pos[0],pos[1],50,paint1);
        float degress = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_4);
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Log.d("BBBBB", "w=" + w + "--h=" + h);
        Matrix matrix1 = new Matrix();
        pathMeasure.getMatrix(pathMeasure.getLength() * value, matrix1, PathMeasure.POSITION_MATRIX_FLAG);
        matrix1.postTranslate(-w / 2, -h / 2);
        matrix1.postRotate(degress, pos[0], pos[1]);
        canvas.drawBitmap(bitmap, matrix1, null);
    }

    public void setVal(float val) {
        this.value = val;
        invalidate();
    }

    private void init() {
        if (paint == null) {
            paint = new Paint();
        }
        if (paint1 == null) {
            paint1 = new Paint();
        }
        path = new Path();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint1.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint1.setStrokeWidth(5);
    }
}
