package com.example.bxh.sayhello;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by bxh on 1/12/17.
 */

public class OtherTest {
    private static final String TAG = "OtherTest";
    static final int[] a = {0,1,2,3,4,5};
    public static void test(){
        ArrayList l;
        for (int i = 0; i < a.length; i++) {
            removeE(a,i);
            copy(a,i);
        }
    }

    public static void removeE(int[] array,int index){
        int[] a = array.clone();
        int s = a.length;
        if (index >= s) {
            //
        }
        @SuppressWarnings("unchecked") int result =  a[index];
        System.arraycopy(a, index + 1, a, index, --s - index);
        Log.i(TAG,"removeE i = "+index+"--a="+new Gson().toJson(a));
    }

    public static void copy(int[] array,int index){
        int[] a = array.clone();
        int s = a.length;
        if (index >= s) {
            //
        }
        @SuppressWarnings("unchecked") int result =  a[index];
        int[]b = Arrays.copyOf(a,index);
        Log.i(TAG,"copy i = "+index+"--b="+new Gson().toJson(b));
    }

}
