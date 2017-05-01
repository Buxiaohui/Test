package com.example.bxh.sayhello.sometest;

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


   public static void testInteger(){
       Integer i1 = 40;
       Integer i2 = 40;
       Integer i3 = 0;
       Integer i4 = new Integer(40);
       Integer i5 = new Integer(40);
       Integer i6 = new Integer(0);
       Integer i7 = new Integer(129);
       Integer i8 = new Integer(129);
       Integer i9 = 129;
       Integer i10 = 129;

       System.out.println(TAG+"i1=i2   " + (i1 == i2));
       System.out.println(TAG+"i1=i2+i3   " + (i1 == i2 + i3));
       System.out.println(TAG+"i1=i4   " + (i1 == i4));
       System.out.println(TAG+"i4=i5   " + (i4 == i5));
       Integer temp = i5+i6;
       System.out.println(TAG+"temp   " + (temp));
       System.out.println(TAG+"i4   " + (i4));
       System.out.println(TAG+"i4=i5+i6   " + (i4 == i5 + i6));
       System.out.println(TAG+"i4=temp   " + (i4 == temp));
       System.out.println(TAG+"i4.equals(i5+i6)   " + (i4.equals(i5+i6)));
       System.out.println(TAG+"40=i5+i6   " + (40 == i5 + i6));
       System.out.println(TAG+"i7=i8   " + (i8==i7));
       System.out.println(TAG+"i8=i9   " + (i8==i9));
       System.out.println(TAG+"i9=i10   " + (i10==i9));
   }

}
