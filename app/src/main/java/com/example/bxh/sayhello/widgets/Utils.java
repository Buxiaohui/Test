package com.example.bxh.sayhello.widgets;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Created by bxh on 3/7/17.
 */

public class Utils {
    public static float getMax(float[] array) {
        float Max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (Max < array[i]) {
                Max = array[i];
            }
        }
        return Max;
    }

    public static float getMin(float[] array) {
        float Max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (Max > array[i]) {
                Max = array[i];
            }
        }
        return Max;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
