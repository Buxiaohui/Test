package com.bxh.designpattern.command;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by buxiaohui on 6/20/17.
 * 命令模式中的Command
 */

public class DrawPathCommand implements IDraw {
    public Path path;//命令模式中的receiver
    public Paint paint;//命令模式中的receiver
    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(path,paint);
    }

    @Override
    public void undo() {

    }
}
