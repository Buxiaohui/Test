package com.example.bxh.sayhello.data;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by buxiaohui on 17-8-22.
 */

public class UserStack<E> {
    private static final String  TAG = "UserStack";
    private static final int DEFAULT_CAPACITY = 8;
    private Object[] mData;
    private int mTopDataIndex = -1;//栈顶元素的序号
    private int mStackSize = 0;   //栈容量
    private int mStackSizeGrow = 10;   //栈扩容

    public UserStack() {
        this(DEFAULT_CAPACITY);
    }

    public UserStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(TAG+",captcity illegal captcity:" + capacity);
        }
        this.mStackSize = capacity;
        mData = new Object[capacity];
    }
    private void init(){
        if (mStackSize <= 0) {
            this.mStackSize = DEFAULT_CAPACITY;
        }
        mData = new Object[DEFAULT_CAPACITY];
    }
    public E push(E e) {
        //empty
        if(mStackSize - 1 == mTopDataIndex && mTopDataIndex == -1) {
            init();
        }
        //full
        if (mStackSize - 1 == mTopDataIndex) {
            System.out.println(TAG+",stack full");
            grow();
        }
        mTopDataIndex += 1;
        mData[mTopDataIndex] = e;
        System.out.println(TAG+",stack push mTopDataIndex="+mTopDataIndex+",mStackSize="+mStackSize);
        return e;
    }

    public E pop() {
        //array not init
        if (mStackSize <= 0) {
            System.out.println(TAG+",stack not init mTopDataIndex="+mTopDataIndex+",mStackSize="+mStackSize);
            return null;
        }
        //content empty
        if(mTopDataIndex < 0){
            System.out.println(TAG+",stack content empty mTopDataIndex="+mTopDataIndex+",mStackSize="+mStackSize);
            return null;
        }
        if(mTopDataIndex>=mStackSize || mTopDataIndex < 0){
            throw new IllegalStateException(TAG+",mTopDataIndex="+mTopDataIndex+",mStackSize="+mStackSize);
        }
        E e = (E) mData[mTopDataIndex];
        mData[mTopDataIndex] = null;
        mTopDataIndex -= 1;
        System.out.println(TAG+",stack pop e="+e+",mTopDataIndex="+mTopDataIndex);
        return e;
    }

    private void grow() {
        Object[] temp = null;
        if (mStackSizeGrow <= 0) {
            temp = new Object[mStackSize + 1];
        } else {
            temp = new Object[mStackSize + mStackSizeGrow];
        }
        System.arraycopy(mData, 0, temp, 0, mStackSize);
        mData = temp;
        mStackSize = mData.length;
    }

    public void clear() {
        mData = null;
    }

    @Override
    public String toString(){
        if(mData == null || mStackSize <= 0){
            return "empty";
        }else {
            //just for test
            return Arrays.toString(mData);
        }
    }
}
