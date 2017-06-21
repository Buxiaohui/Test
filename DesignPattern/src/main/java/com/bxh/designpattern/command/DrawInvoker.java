package com.bxh.designpattern.command;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by buxiaohui on 6/20/17.
 */

public class DrawInvoker {
    private List<DrawPathCommand> drawPathList = Collections.synchronizedList(new ArrayList<DrawPathCommand>());
    private List<DrawPathCommand> redoPathList = Collections.synchronizedList(new ArrayList<DrawPathCommand>());

    public void add(DrawPathCommand command) {
        redoPathList.clear();
        drawPathList.add(command);
    }

    public void undo() {
        if (drawPathList.size() > 0) {
            DrawPathCommand undo = drawPathList.get(drawPathList.size() - 1);
            drawPathList.remove(drawPathList.size() - 1);
            undo.undo();
            redoPathList.add(undo);
        }
    }

    public void redo() {
        if (redoPathList.size() > 0) {
            DrawPathCommand undo = redoPathList.get(redoPathList.size() - 1);
            redoPathList.remove(redoPathList.size() - 1);
            drawPathList.add(undo);
        }
    }

    public boolean canRedo() {
        return redoPathList.size() > 0;
    }

    public boolean canUndo() {
        return drawPathList.size() > 0;
    }

    public void excute(Canvas canvas) {
        if (drawPathList != null) {
            for (DrawPathCommand path : drawPathList) {
                path.draw(canvas);
            }
        }
    }
}
