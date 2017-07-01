package com.example.bxh.sayhello.color;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
//import android.support.v7.graphics.Palette;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bxh.sayhello.R;

public class ColorActivity extends Activity {
    TextView textView;
    TextView textView2;
    int offset;
    int startColor = Color.argb(255, 100, 50, 0);
    int endColor = Color.argb(255, 200, 210, 17);
    Integer currentColor;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator() {
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            int startInt = (Integer) startValue;
            int startA = (startInt >> 24) & 0xff;
            int startR = (startInt >> 16) & 0xff;
            int startG = (startInt >> 8) & 0xff;
            int startB = startInt & 0xff;

            int endInt = (Integer) endValue;
            int endA = (endInt >> 24) & 0xff;
            int endR = (endInt >> 16) & 0xff;
            int endG = (endInt >> 8) & 0xff;
            int endB = endInt & 0xff;
            Log.i("BBBBB","(endA - startA)="+(endA - startA));
            return (int)((startA + (int)((endA - startA))) << 24) |
                    (int)((startR + (int)(fraction * (endR - startR))) << 16) |
                    (int)((startG + (int)(fraction * (endG - startG))) << 8) |
                    (int)((startB + (int)(fraction * (endB - startB))));
        }
    };
    boolean flag;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ListView l;
            super.handleMessage(msg);
            if(!flag){
                offset++;
                if(offset == 100){
                    flag = true;
                }
            }else{
                offset --;
                if(offset == 0){
                    flag = false;
                }
            }
            float offsetF = offset / 100f;
            textView2.setText(offsetF+"");

            currentColor = (Integer) argbEvaluator.evaluate(offset / 100f, startColor, endColor);
            sendEmptyMessageDelayed(0, 30);
            textView.setBackgroundColor(currentColor);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        textView = (TextView) findViewById(R.id.text1);
        textView2 = (TextView) findViewById(R.id.text2);
        handler.sendEmptyMessageDelayed(0, 0);
    }

    /*private void paletteTest(){
        Bitmap bitmap = null;
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //1.活力颜色
                Palette.Swatch a = palette.getVibrantSwatch();
                //2.亮的活力颜色
                Palette.Swatch b=palette.getLightVibrantSwatch();
                //3.暗的活力颜色
                Palette.Swatch c = palette.getDarkVibrantSwatch();
                //4.柔色
                Palette.Swatch d = palette.getMutedSwatch();
                //5.亮的柔色
                Palette.Swatch e = palette.getLightMutedSwatch();
                //6.暗的柔色
                Palette.Swatch f = palette.getDarkMutedSwatch();
                f.getRgb(); //rgb颜色
                f.getTitleTextColor();//文本颜色

                //返回float[]，可以进行修改，后使用ColorUtils工具类进行转换
                f.getHsl();
                f.getBodyTextColor();//和文本颜色一样
            }
        });
    }*/

}