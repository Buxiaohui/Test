package com.example.bxh.sayhello.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private Paint mDividerPaint;

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
        drawDivider(canvas);
        drawLine(canvas);
    }

    private void drawDivider(Canvas canvas) {
        if (mValLength <= 0) {
            return;
        }
        Log.i(TAG, "drawDivider mValLength=" + mValLength);
        int columnLineCount = mValLength;
        int rowLineCount = (int) mMaxValBoundary;
        xOffset = (mCanvasWidth - DIVIDER_WIDTH) / columnLineCount;
        yOffset = (mCanvasHeight - DIVIDER_WIDTH) / rowLineCount;
        Log.i(TAG, "drawDivider xOffset=" + xOffset);
        Log.i(TAG, "drawDivider mCanvasHeight=" + mCanvasHeight + "--mCanvasWidth=" + mCanvasWidth);
        for (int i = 0; i < columnLineCount + 1; i++) {
            //'+DIVIDER_WIDTH'防止第一条线不完整
            Log.i(TAG, "drawDivider columnLine i=" + i);
            canvas.drawLine(i * xOffset + DIVIDER_WIDTH / 2, 0, i * xOffset + DIVIDER_WIDTH / 2, mCanvasHeight, mDividerPaint);
        }

        for (int i = 0; i < rowLineCount + 1; i++) {
            //'+DIVIDER_WIDTH'防止第一条线不完整
            Log.i(TAG, "drawDivider rowLine i=" + i);
            canvas.drawLine(0, i * yOffset + DIVIDER_WIDTH / 2, mCanvasWidth, i * yOffset + DIVIDER_WIDTH / 2, mDividerPaint);
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

    private void initDividerPaint() {
        if (mDividerPaint == null) {
            mDividerPaint = new Paint();
        }
        mDividerPaint.setAntiAlias(true);
        mDividerPaint.setColor(Color.BLACK);
        mDividerPaint.setStrokeWidth(DIVIDER_WIDTH);//just test
        mDividerPaint.setStyle(Paint.Style.STROKE);
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



