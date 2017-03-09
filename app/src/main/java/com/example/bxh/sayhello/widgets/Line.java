package com.example.bxh.sayhello.widgets;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by bxh on 3/7/17.
 */

public class Line {
    private static final String TAG = "Line";
    private Paint mNodePaint;
    private Paint mConnectionPaint;
    private Paint mNodeTextPaint;
    private float[] values;
    private LineNode[] nodes;
    private int mNodeShape;
    private float mMaxVal;
    private float mMinVal;

    public Line(LineBuilder builder) {
        this.mNodeTextPaint = builder.getNodeTextPaint();
        this.mNodePaint = builder.getNodePaint();
        this.mConnectionPaint = builder.getConnectionPaint();
        this.values = builder.getValues();
        this.mNodeShape = builder.getNodeShape();
    }

    public LineNode[] getNodes() {
        return nodes;
    }


    public float getMinVal() {
        if (mMinVal == 0f) {
            mMinVal = Utils.getMax(values);
        }
        return mMinVal;
    }

    public Line setMinVal(float mMinVal) {
        this.mMinVal = mMinVal;
        return this;
    }

    public float getMaxVal() {
        if (mMaxVal == 0f) {
            mMaxVal = Utils.getMax(values);
        }
        return mMaxVal;
    }

    public Line setMaxVal(float mMaxVal) {
        this.mMaxVal = mMaxVal;
        return this;
    }

    public float[] getValues() {
        return values;
    }


    public void drawConnection(Canvas canvas) {
        for (int j = 1; j < nodes.length; j++) {
            LineNode pre = nodes[j - 1];
            LineNode cur = nodes[j];
            canvas.drawLine(pre.getNodeCenterX(), pre.getNodeCenterY(), cur.getNodeCenterX(), cur.getNodeCenterY(), mConnectionPaint);
        }
    }

    /**
     * must called first
     */
    public void calculateAndDrawAll(Canvas canvas,float xOffset, double mMaxValBoundary, int mCanvasHeight) {
        calculate(xOffset, mMaxValBoundary, mCanvasHeight);
        drawAll(canvas);
    }
    public void calculate(float xOffset, double mMaxValBoundary, int mCanvasHeight) {
        LineNode[] lineNodes = new LineNode[values.length];
        if (values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                float yPos = mCanvasHeight * (1f - values[i] / ((float) mMaxValBoundary));
                float xPos = xOffset * i;
                lineNodes[i] = new LineNode(xPos, yPos);
                Log.i(TAG, "drawtNode i=" + i + "--xPos=" + xPos + "--yPos=" + yPos);
            }
        }
        nodes = lineNodes;
    }

    public void drawNode(Canvas canvas) {
        if (values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                LineNode node = nodes[i];
                switch (mNodeShape) {
                    case ShapeType.CIRCLE:
                        canvas.drawCircle(node.getNodeCenterX(), node.getNodeCenterY(), 4, mNodePaint);
                        break;
                    default:
                        canvas.drawCircle(node.getNodeCenterX(), node.getNodeCenterY(), 4, mNodePaint);
                        break;
                }

            }
        }
    }

    public void drawNodeText(Canvas canvas) {
        for (int i = 0; i < values.length; i++) {
            LineNode node = nodes[i];
            canvas.drawText("" + values[i], node.getNodeCenterX(), node.getNodeCenterY(), mNodeTextPaint);
        }
    }


    public void drawAll(Canvas canvas) {
        drawConnection(canvas);
        drawNode(canvas);
        drawNodeText(canvas);
    }


}
