package com.example.bxh.sayhello.genericity;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by buxiaohui on 6/19/17.
 */

public class GenericityTestA<T> {
    private static final String TAG = "GenericityTestA";
    /**
     * 经常发现有List<? super T>、Set<? extends T>的声明，是什么意思呢？
     * <? super T>表示包括T在内的任何T的父类，
     * <? extends T>表示包括T在内的任何T的子类
     */
    List<? super T> list1;
    List<? extends T> list2;

    public List<?> getList3() {
        return list3;
    }

    public void setList3(List<?> list3) {
        this.list3 = list3;
    }

    List<?> list3;

    public List<? super T> getList1() {
        return list1;
    }

    public void setList1(List<? super T> list1) {
        this.list1 = list1;
    }

    public List<? extends T> getList2() {
        return list2;
    }

    public void setList2(List<? extends T> list2) {
        this.list2 = list2;
    }
    public void printList(){
        if(list1 != null){
            Log.i(TAG,"list1="+ Arrays.toString(list1.toArray()));
        }else{
            Log.i(TAG,"list1=null");
        }

        if(list2 != null){
            Log.i(TAG,"list2="+ Arrays.toString(list2.toArray()));
        }else{
            Log.i(TAG,"list2=null");
        }

        if(list3 != null){
            Log.i(TAG,"list3="+ Arrays.toString(list3.toArray()));
        }else{
            Log.i(TAG,"list3=null");
        }
    }
}
