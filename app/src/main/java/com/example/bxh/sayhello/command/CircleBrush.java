package com.example.bxh.sayhello.command;

import android.graphics.Path;

/**
 * Created by buxiaohui on 6/20/17.
 */

public class CircleBrush implements IBrush {
    @Override
    public void down(Path path, float x, float y) {

    }

    @Override
    public void up(Path path, float x, float y) {

    }

    @Override
    public void move(Path path, float x, float y) {
       path.addCircle(x,y,10,Path.Direction.CW);
    }
}
