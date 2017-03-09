package com.example.bxh.sayhello.widgets;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by bxh on 3/8/17.
 */

public class LineBuilder {
    private Paint mNodePaint;
    private Paint mConnectionPaint;
    private Paint mNodeTextPaint;
    private float[] values;
    private int mNodeShape;

    public LineBuilder() {
        mNodeShape = ShapeType.CIRCLE;
    }

    public Paint getNodePaint() {
        return mNodePaint;
    }

    public LineBuilder setNodePaint(Paint mNodePaint) {
        this.mNodePaint = mNodePaint;
        return this;
    }

    public Paint getConnectionPaint() {
        return mConnectionPaint;
    }

    public LineBuilder setConnectionPaint(Paint mConnectionPaint) {
        this.mConnectionPaint = mConnectionPaint;
        return this;
    }

    public Paint getNodeTextPaint() {
        return mNodeTextPaint;
    }

    public LineBuilder setNodeTextPaint(Paint mNodeTextPaint) {
        this.mNodeTextPaint = mNodeTextPaint;
        return this;
    }

    public float[] getValues() {
        return values;
    }

    public LineBuilder setValues(float[] values) {
        this.values = values;
        return this;
    }

    public int getNodeShape() {
        return mNodeShape;
    }

    public LineBuilder setNodeShape(int mNodeShape) {
        this.mNodeShape = mNodeShape;
        return this;
    }

    public Paint getDefaultNodeTextPaint() {
        if (mNodeTextPaint == null) {
            mNodeTextPaint = new Paint();
            mNodeTextPaint.setColor(Color.BLACK);
            mNodeTextPaint.setAntiAlias(true);
            mNodeTextPaint.setStrokeWidth(1);//just test
            mNodeTextPaint.setStyle(Paint.Style.STROKE);
            mNodeTextPaint.setTextAlign(Paint.Align.CENTER);
            mNodeTextPaint.setTextSize(100);
            mNodeTextPaint.setUnderlineText(true);
        }
        return mNodeTextPaint;
    }

    public Paint getDefaultConnectionPaint() {
        if (mConnectionPaint == null) {
            mConnectionPaint = new Paint();
            mConnectionPaint.setColor(Color.BLACK);
            mConnectionPaint.setAntiAlias(true);
            mConnectionPaint.setStrokeWidth(2);//just test
            mConnectionPaint.setStyle(Paint.Style.STROKE);
        }
        return mConnectionPaint;
    }

    public Paint getDefaultNodePaint() {
        if (mNodePaint == null) {
            mNodePaint = new Paint();
            mNodePaint.setColor(Color.BLACK);
            mNodePaint.setAntiAlias(true);
            mNodePaint.setStrokeWidth(10);//just test
            mNodePaint.setStyle(Paint.Style.STROKE);
        }
        return mNodePaint;
    }

    public Line build() {
        if (mConnectionPaint == null) {
            mConnectionPaint = getDefaultConnectionPaint();
        }

        if (mNodePaint == null) {
            mNodePaint = getDefaultNodePaint();
        }

        if (mNodeTextPaint == null) {
            mNodeTextPaint = getDefaultNodeTextPaint();
        }

        Line line = new Line(this);
        return line;

    }
}
