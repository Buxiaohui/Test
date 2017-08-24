package com.example.bxh.sayhello.data;

import java.util.Arrays;

/**
 * Created by buxiaohui on 17-8-24.
 */

public class UserQueue<E> {
    public static final String TAG = "UserQueue";
    private final static int DEFAULT_CAPACITY = 10;
    Object[] mData;
    int mRealDataLen;//数组中数据长度，不包括null
    int mDataLen;//数组长度
    int mRealHeadIndex;//头指针的index
    int mRealTailIndex;//尾指针的index
    private int mGrowCapacity;

    public UserQueue(int capacity, int mGrowCapacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("illegal capacity:" + capacity + "");
        }
        if (mGrowCapacity < 0) {
            throw new IllegalArgumentException("illegal mGrowCapacity:" + mGrowCapacity);
        }
        this.mDataLen = capacity;
        this.mGrowCapacity = mGrowCapacity;
        mData = new Object[capacity];
    }

    public UserQueue(int capacity) {
        this(capacity, 0);
    }

    public UserQueue() {
        this(DEFAULT_CAPACITY);
    }

    public void add(E e) {
        System.out.println(TAG + ",queue add e=" + e + ",mRealTailIndex=" + mRealTailIndex + ",mDataLen=" + mDataLen);
        ensureExpan();
        System.out.println(TAG + ",queue add e=" + e + ",mRealTailIndex=" + mRealTailIndex + ",mDataLen=" + mDataLen);
        mData[++mRealTailIndex] = e;
        mRealDataLen = mRealTailIndex - mRealHeadIndex;
    }

    public void ensureExpan() {
        boolean isTailBorder = (mRealTailIndex == mDataLen - 1);
        if (isTailBorder) {//尾指针到达最后
            toString();
            System.out.println(TAG + ",queue ensureExpan mRealTailIndex=" + mRealTailIndex + ",mDataLen=" + mDataLen + ",mRealDataLen=" + mRealDataLen + ",mRealHeadIndex=" + mRealHeadIndex);
            if (mRealHeadIndex == 0) {//头指针在第一位，说明数据全部填满，需要扩容
                int newSize = mGrowCapacity > 0 ? mDataLen + mGrowCapacity : mDataLen << 1;
                Object[] temp = new Object[newSize];
                System.arraycopy(mData, 0, temp, 0, mDataLen);
                mData = temp;
                mDataLen = newSize;
            } else { //头指针不在第一位，数据未填满，把数据块移动到头
                System.arraycopy(mData, mRealHeadIndex, mData, 0, mRealDataLen);
                mRealHeadIndex = 0;
                mRealTailIndex = mRealDataLen - 1;
            }
        }
    }

    public E remove() {
        E e = null;
        if (mRealHeadIndex < mDataLen) {
            if (mRealHeadIndex < mRealTailIndex) {
                e = (E) mData[mRealHeadIndex];
                mData[mRealHeadIndex] = null;
                mRealHeadIndex++;
            } else {
                e = (E) mData[mRealHeadIndex];
                mData[mRealHeadIndex] = null;
            }
        }
        if (mRealHeadIndex == mDataLen - 1) {
            if (mRealHeadIndex == mRealTailIndex) {
                e = (E) mData[mRealHeadIndex];
                mData[mRealHeadIndex] = null;
            }
        }
        mRealDataLen = mRealTailIndex - mRealHeadIndex;
        return e;
    }

    @Override
    public String toString(){
     return mData!=null ? Arrays.toString(mData) :" content is null";
    }

}
