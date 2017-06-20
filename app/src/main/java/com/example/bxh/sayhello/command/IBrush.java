package com.example.bxh.sayhello.command;

import android.graphics.Path;

/**
 * Created by buxiaohui on 6/20/17.
 */

public interface IBrush {
    void down(Path path,float x,float y);
    void up(Path path,float x,float y);
    void move(Path path,float x,float y);
}
