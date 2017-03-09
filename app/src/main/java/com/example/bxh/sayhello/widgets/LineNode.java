package com.example.bxh.sayhello.widgets;

/**
 * Created by bxh on 3/7/17.
 */

public class LineNode {
    private float mNodeCenterX;
    private float mNodeCenterY;

    public LineNode(float mNodeCenterX, float mNodeCenterY) {
        this.mNodeCenterX = mNodeCenterX;
        this.mNodeCenterY = mNodeCenterY;
    }

    public float getNodeCenterY() {
        return mNodeCenterY;
    }

    public LineNode setNodeCenterY(int mNodeCenterY) {
        this.mNodeCenterY = mNodeCenterY;
        return this;
    }

    public LineNode setmNodeCenterX(int mNodeCenterX) {
        this.mNodeCenterX = mNodeCenterX;
        return this;
    }

    public LineNode setNodeCenterXY(float mNodeCenterX, float mNodeCenterY) {
        this.mNodeCenterX = mNodeCenterX;
        this.mNodeCenterY = mNodeCenterY;
        return this;
    }

    public float getNodeCenterX() {
        return mNodeCenterX;
    }
}
