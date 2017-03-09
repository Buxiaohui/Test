package com.example.bxh.sayhello.widgets;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.example.bxh.sayhello.widgets.ShapeType.CIRCLE;
import static com.example.bxh.sayhello.widgets.ShapeType.REACT;
import static com.example.bxh.sayhello.widgets.ShapeType.RECTANGLE;

/**
 * Created by bxh on 3/7/17.
 */
@IntDef({CIRCLE,RECTANGLE,REACT})
@Retention(RetentionPolicy.SOURCE)
public @interface ShapeType {
    int CIRCLE = 1;
    int RECTANGLE = 2;
    int REACT = 3;
}
