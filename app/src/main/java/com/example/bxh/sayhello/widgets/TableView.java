package com.example.bxh.sayhello.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bxh on 3/7/17.
 */

/**
 * v1.0
 * 暂时不考虑复杂情况，默认全是正数
 */
public class TableView extends View {
    private static final int DIVIDER_WIDTH = 2;//px
    private static final String TAG = "TableView";
    private List<Line> mList;
    private String yName;//y轴的单位名称
    private String xName;
    private float xOffset;
    private float yOffset;
    private float mMaxVal;
    private float mMinVal;
    private double mMinValBoundary;
    private double mMaxValBoundary;
    private boolean mIsMeasure = true;
    private int mCanvasHeight;
    private int mCanvasWidth;
    private int mValLength;
    private Paint mXYPaint;
    private Paint mDividerPaint;
    private float bottomMargin = 100;//px
    private float leftMargin = 100;//px
    private float topMargin = 100;//px
    private float rightMargin = 100;//px
    private int horizontalLineCount = 10;
    private int verticalLineCount = 12;
    private int yAxisTickMarkCount = horizontalLineCount * 2;
    private int xAxisTickMarkCount = verticalLineCount;
    private float tickMarkHeight = 10;

    public TableView(Context context) {
        super(context);
    }

    public TableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (mIsMeasure) {
            this.mCanvasHeight = getHeight();
            this.mCanvasWidth = getWidth();
            mIsMeasure = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initDividerPaint();
        initXYPaint();
        drawCoordinate(canvas);
        //drawLine(canvas);
    }

    private void drawCoordinate(Canvas canvas) {
        if (mValLength <= 0) {
            return;
        }
        drawXLine(canvas);
        drawYLine(canvas);
        drawHorizontalLines(canvas);
        drawXTickMarks(canvas);
        drawYTickMarks(canvas);
        drawXTickMarksText(canvas);
        drawYTickMarksText(canvas);

    }

    private void drawYLine(Canvas canvas) {
        float startY = (mCanvasHeight - bottomMargin);
        float startX = leftMargin;
        float stopX = startX;
        float stopY = topMargin;
        canvas.drawLine(startX, startY, stopX, stopY, mXYPaint);
    }

    private void drawXLine(Canvas canvas) {
        float startY = (mCanvasHeight - bottomMargin);
        float startX = leftMargin;
        float stopX = mCanvasWidth - rightMargin;
        float stopY = startY;
        canvas.drawLine(startX, startY, stopX, stopY, mXYPaint);
    }

    private void drawHorizontalLines(Canvas canvas) {

        float startX = leftMargin;
        float stopX = mCanvasWidth - rightMargin;
        //主要是Y的计算
        for (int i = 0; i < horizontalLineCount; i++) {
            float startY = getHorizonalLineY(i);
            float stopY = startY;
            Path path = new Path();
            path.moveTo(startX, startY);
            path.lineTo(stopX, stopY);
            canvas.drawPath(path, mDividerPaint);
        }

    }

    private float getHorizonalLineY(int index) {
        float y;
        float yGap = (mCanvasHeight - bottomMargin - topMargin) / horizontalLineCount;
        y = mCanvasHeight - bottomMargin - yGap * ((float) index + 1f);
        return y;
    }

    private float getTickLineX(int index) {
        float x;
        float xGap = (mCanvasWidth - leftMargin - rightMargin) / xAxisTickMarkCount;
        x = leftMargin + xGap * ((float) index);
        return x;
    }

    private float getTickLineY(int index) {
        float y;
        float yGap = (mCanvasHeight - bottomMargin - topMargin) / yAxisTickMarkCount;
        y = mCanvasHeight - bottomMargin - yGap * ((float) index);
        return y;
    }

    private void drawYTickMarks(Canvas canvas) {
        float startX = leftMargin;
        float stopX = startX - tickMarkHeight;
        for (int i = 0; i < yAxisTickMarkCount; i++) {
            float startY = getTickLineY(i);
            float stopY = startY;
            canvas.drawLine(startX, startY, stopX, stopY, mXYPaint);
        }
    }

    private void drawXTickMarks(Canvas canvas) {
        float startY = (mCanvasHeight - bottomMargin);
        float stopY = startY + tickMarkHeight;
        for (int i = 0; i < xAxisTickMarkCount; i++) {
            float startX = getTickLineX(i);
            float stopX = startX;
            canvas.drawLine(startX, startY, stopX, stopY, mXYPaint);
        }
    }

    private void drawXTickMarksText(Canvas canvas) {
        float startY = (mCanvasHeight - bottomMargin);
        float stopY = startY + tickMarkHeight;
        for (int i = 0; i < xAxisTickMarkCount; i++) {
            float startX = getTickLineX(i);
            float stopX = startX;
            canvas.drawText("" + i, startX, startY+50, mXYPaint);
        }
    }

    private void drawYTickMarksText(Canvas canvas) {
        float startX = leftMargin;
        float stopX = startX - tickMarkHeight;
        for (int i = 0; i < yAxisTickMarkCount; i++) {
            float startY = getTickLineY(i);
            float stopY = startY;
            canvas.drawText("" + i, startX-50, startY, mXYPaint);
        }
    }

    private void drawLine(Canvas cavans) {
        for (Line line : mList) {
            if (line != null) {
                line.calculateAndDrawAll(cavans, xOffset, mMaxValBoundary, mCanvasHeight);
            }
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initXYPaint() {
        if (mXYPaint == null) {
            mXYPaint = new Paint();
        }
        mXYPaint.setAntiAlias(true);
        mXYPaint.setColor(Color.BLACK);
        mXYPaint.setStrokeWidth(DIVIDER_WIDTH);//just test
        mXYPaint.setStyle(Paint.Style.STROKE);
    }

    private void initDividerPaint() {
        if (mDividerPaint == null) {
            mDividerPaint = new Paint();
        }
        PathEffect effects = new DashPathEffect(new float[]{1, 2}, 1);
        mDividerPaint.setPathEffect(effects);
        mDividerPaint.setAntiAlias(true);
        mDividerPaint.setColor(Color.BLACK);
        mDividerPaint.setStrokeWidth(DIVIDER_WIDTH);//just test
        mDividerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void addLine(Line line) {
        if (mList == null) {
            mList = new ArrayList<Line>();
        }
        mList.add(line);
        calculateBoundaryValue();
        invalidate();
    }


    private void calculateBoundaryValue() {
        for (Line line : mList) {
            if (line != null) {
                if (line.getMaxVal() > mMaxVal) {
                    mMaxVal = line.getMaxVal();
                }
                if (line.getMinVal() > mMinVal) {
                    mMinVal = line.getMinVal();
                }
                if (line.getValues() != null && line.getValues().length > mValLength) {
                    mValLength = line.getValues().length;
                }
            }
        }
        mMaxValBoundary = Math.ceil(mMaxVal);
        mMinValBoundary = Math.floor(mMaxVal);

    }

}



