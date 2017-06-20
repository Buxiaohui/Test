package com.example.bxh.sayhello.command;

import android.graphics.Path;

/**
 * Created by buxiaohui on 6/20/17.
 * Receiver1
 */

public class NormalBrush implements IBrush {
    @Override
    public void down(Path path, float x, float y) {
           path.moveTo(x,y);
    }

    @Override
    public void up(Path path, float x, float y) {

    }

    @Override
    public void move(Path path, float x, float y) {
       path.lineTo(x,y);
    }
}
