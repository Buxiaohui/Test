package com.example.bxh.sayhello.command;

import android.graphics.Canvas;

/**
 * Created by buxiaohui on 6/20/17.
 */

public interface IDraw {
    void draw(Canvas canvas);
    void undo();
}
