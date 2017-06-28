package com.example.bxh.sayhello.tree;

/**
 * Created by bxh on 6/28/17.
 * 来源：http://www.cnblogs.com/xp12/p/4264773.html
 * 将无序数组转为最小堆
 */

public class MinHeap {
    private int[] data;

    public MinHeap(int[] data) {
        this.data = data;
    }

    public static void test() {
        int[] value = {10, 100, 12, 73, 45, 32, 11, 23, 55, 34, 90, 21};
        MinHeap heap = new MinHeap(value);
        heap.createHeap();
        System.out.println("MinHeap--test start");
        for (int i = 0; i < value.length; i++) {
            System.out.print("MinHeap--" + heap.data[i] + " ");
        }
        System.out.println();
        System.out.println("MinHeap--test setRoot");
        heap.setRoot(64);
        for (int i = 0; i < value.length; i++) {
            System.out.print("MinHeap--" + heap.data[i] + " ");
        }
        System.out.println();
        System.out.println("MinHeap--test end");
    }

    /**
     * 无序数组--->最小堆
     * */
    public void createHeap() {
        // 从最后一个有孩子的父节点处开始
        // 用他的左右孩子位置，与父节点位置的值作对比
        for (int i = (data.length) / 2 - 1; i >= 0; i--) {
            heapIfy(i);
        }
    }

    public void heapIfy(int value) {
        int lchild = left(value);
        int rchild = right(value);
        int smallest = value;
        if (lchild < data.length && data[lchild] < data[value])
            smallest = lchild;
        if (rchild < data.length && data[rchild] < data[smallest])
            smallest = rchild;
        if (value == smallest){
            return;
        }
        swap(value, smallest);
        // 从最后一个有孩子的父节点处开始，作为参考值
        // 用他的左右孩子位置，与父节点位置的值作对比
        // 对比结束后，还要把被换下去的，即，放在子节点位置的值，作为参考点，继续向下递归
        heapIfy(smallest);
    }

    public int left(int value) {
        return ((value + 1) << 1) - 1;
    }

    public int right(int value) {
        return (value + 1) << 1;
    }

    public void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void setRoot(int value) {
        data[0] = value;
        heapIfy(0);
    }
}