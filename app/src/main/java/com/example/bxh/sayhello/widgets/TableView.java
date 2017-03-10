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
 * 暂时不考虑复杂情况
 */
public class TableView extends View {
    private static final int DIVIDER_WIDTH = 2;//px
    private static final String TAG = "TableView";
    String[] mYNums;
    int mLineOffset;
    int mTextOffset;
    private List<Line> mList;
    private String yName;//y轴的单位名称
    private String xName;
    private float xOffset;
    private float yOffset;
    private float mMaxVal = 20;
    private float mMinVal = 0;
    private double mMinValBoundary;
    private double mMaxValBoundary;
    private boolean mIsMeasure = true;
    private int mCanvasHeight;
    private int mCanvasWidth;
    private int mValLength;
    private Paint mXYPaint;
    private Paint mTickMarkPaint;
    private Paint mDividerPaint;
    private Paint mGuardLinePaint;
    private float bottomMargin = 100;//px
    private float leftMargin = 100;//px
    private float topMargin = 100;//px
    private float rightMargin = 100;//px
    private int horizontalLineCount = 10;
    private int verticalLineCount = 12;
    private int yAxisTickMarkCount = horizontalLineCount * 2;
    private int xAxisTickMarkCount = verticalLineCount;
    private float tickMarkHeight = 10;
    private float mGuardLineVal;

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
        initTickMarkPaint();
        initGuardLinePaint();
        drawCoordinate(canvas);
        drawLine(canvas);
    }

    private void drawCoordinate(Canvas canvas) {
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
            // x-axis 上不画虚线
            float startY = getHorizonalLineY(i + 1);
            float stopY = startY;
            Path path = new Path();
            path.moveTo(startX, startY);
            path.lineTo(stopX, stopY);
            canvas.drawPath(path, mDividerPaint);
        }

    }

    private void drawGuardLine(Canvas canvas) {
        float startX = leftMargin;
        float stopX = mCanvasWidth - rightMargin;
        //主要是Y的计算
        float startY = getGuradLineY();
        float stopY = startY;
        Path path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(stopX, stopY);
        canvas.drawPath(path, mGuardLinePaint);
    }

    private float getGuradLineY() {
        float percent = mGuardLineVal / (mMaxVal - mMinVal);
        float y = (float)mCanvasHeight - bottomMargin - percent * (mCanvasHeight - bottomMargin - topMargin);
        return y;
    }

    public float getmGuardLineVal() {
        return mGuardLineVal;
    }

    public TableView setmGuardLineVal(float mGuardLineVal) {
        this.mGuardLineVal = mGuardLineVal;
        return this;
    }

    private float getHorizonalLineY(int index) {
        float y;
        float yGap = (mCanvasHeight - bottomMargin - topMargin) / horizontalLineCount;
        y = mCanvasHeight - bottomMargin - yGap * ((float) index);
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
            float startY = getTickLineY(i + 1);
            float stopY = startY;
            canvas.drawLine(startX, startY, stopX, stopY, mXYPaint);
        }
    }

    private void drawXTickMarks(Canvas canvas) {
        float startY = (mCanvasHeight - bottomMargin);
        float stopY = startY + tickMarkHeight;
        for (int i = 0; i < xAxisTickMarkCount; i++) {
            // x-axis 坐标原点 上不画线
            float startX = getTickLineX(i + 1);
            float stopX = startX;
            canvas.drawLine(startX, startY, stopX, stopY, mXYPaint);
        }
    }

    private void drawXTickMarksText(Canvas canvas) {
        float startY = (mCanvasHeight - bottomMargin);
        float stopY = startY + tickMarkHeight;
        for (int i = 0; i < xAxisTickMarkCount + 1; i++) {
            float startX = getTickLineX(i);
            float stopX = startX;
            canvas.drawText("" + i, startX, startY + 50, mTickMarkPaint);
        }
    }

    private void drawYTickMarksText(Canvas canvas) {
        float startX = leftMargin;
        float stopX = startX - tickMarkHeight;
        for (int i = 0; i < yAxisTickMarkCount + 1; i++) {
            float startY = getTickLineY(i);
            float stopY = startY;
            canvas.drawText("" + i, startX - 50, startY, mTickMarkPaint);
        }
    }


    private void drawLine(Canvas cavans) {
        float min = 0f;
        float max = 19f;
        float leftMargin = this.leftMargin;
        float bottomMargin = this.bottomMargin;
        float topMargin = this.topMargin;
        float rightMargin = this.rightMargin;
        float[] margins = {leftMargin, topMargin, rightMargin, bottomMargin};
        float xgap = (mCanvasWidth - this.leftMargin - this.rightMargin) / xAxisTickMarkCount;
        for (Line line : mList) {
            if (line != null) {
                line.calculateAndDrawAll(cavans, min, max, mCanvasHeight, xgap, margins);
            }
        }
        drawGuardLine(cavans);
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

    private void initTickMarkPaint() {
        if (mTickMarkPaint == null) {
            mTickMarkPaint = new Paint();
        }
        mTickMarkPaint.setAntiAlias(true);
        mTickMarkPaint.setTextSize(30);
        mTickMarkPaint.setColor(Color.BLACK);
        mTickMarkPaint.setStrokeWidth(DIVIDER_WIDTH);//just test
        mTickMarkPaint.setStyle(Paint.Style.STROKE);
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

    private void initGuardLinePaint() {
        if (mGuardLinePaint == null) {
            mGuardLinePaint = new Paint();
        }
        PathEffect effects = new DashPathEffect(new float[]{1, 2}, 1);
        mGuardLinePaint.setPathEffect(effects);
        mGuardLinePaint.setAntiAlias(true);
        mGuardLinePaint.setColor(Color.RED);
        mGuardLinePaint.setStrokeWidth(4);//just test
        mGuardLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
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


    }

    /**
     * Y轴
     */
    private void setYNums(String[] mYNums, int lineOffset, int textOffset) {
        this.mYNums = mYNums;
        this.mLineOffset = lineOffset;
        this.mTextOffset = textOffset;
    }


}



