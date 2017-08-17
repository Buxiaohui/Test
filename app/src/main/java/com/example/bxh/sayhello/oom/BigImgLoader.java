package com.example.bxh.sayhello.oom;

import android.graphics.BitmapRegionDecoder;

/**
 * Created by buxiaohui on 17-8-12.
 */

/**
 *!!!!!加载超大图!!!!!
 * BitmapRegionDecoder can be used to decode a rectangle region from an image.
 * BitmapRegionDecoder is particularly useful when an original image is large and
 * you only need parts of the image.
 *
 * <p>To create a BitmapRegionDecoder, call newInstance(...).
 * Given a BitmapRegionDecoder, users can call decodeRegion() repeatedly
 * to get a decoded Bitmap of the specified region.
 *
 * 可以参考http://blog.csdn.net/jjmm2009/article/details/49360751
 * http://blog.csdn.net/lmj623565791/article/details/49300989/
 * https://github.com/johnnylambada/WorldMap
 */
public class BigImgLoader {
    public static void loadBigImg(){
        BitmapRegionDecoder bitmapRegionDecoder = null;
    }
}
