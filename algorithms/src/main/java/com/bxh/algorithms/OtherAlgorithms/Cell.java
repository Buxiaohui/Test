package com.bxh.algorithms.OtherAlgorithms;

/**
 * Created by bxh on 7/26/17.
 */

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 一个细胞，每小时分裂成两个，分裂三次以后，本体会死掉。
 * n个小时之后，有多少个细胞？
 * 其实是一个二叉树的结构，我觉得可以利用二叉树结构计算结果，但是我没有利用二叉树，而是使用队列来做的，方法不好，耗内存，待优化。
 */
public class Cell {


    public static void getCellCount() {
        for (int i = 0; i < 10; i++) {
            System.out.println("cell with time -----------");
            int count = new Cell().getCellCount(i);
            System.out.println("cell with time i=" + i + "  count=" + count);
        }
        System.out.println("--------cell count----getCellCount1------");
        System.out.println("--------cell count-----getCellCount1-----");
        System.out.println("--------cell count------getCellCount1----");
        for (int i = 0; i < 10; i++) {
            System.out.println("cell with time -----------");
            int count = new Cell().getCellCount1(i);
            System.out.println("cell with time i=" + i + "  count=" + count);
        }
        System.out.println("--------cell count-----getCellCount2-----");
        System.out.println("--------cell count------getCellCount2----");
        System.out.println("--------cell count-------getCellCount2---");
        for (int i = 0; i < 10; i++) {
            System.out.println("cell with time -----------"+(i+1));
            int count = new Cell().getCellCount2(i+1, 0);
            System.out.println("cell with time count=" + count);
        }
    }

    public int getCellCount1(int n) {

        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 2;
        } else if (n == 2) {
            return 4;
        } else if (n == 3) {
            return 7;
        }
        int[] count = new int[n + 1];
        count[0] = 1;//第0次
        count[1] = 2;//第1次
        count[2] = 4;//第2次
        count[3] = 7;//第3次

        int[] invalidCount = new int[n + 1];//每次分裂要去掉的节点数
        invalidCount[0] = 0;//第0次
        invalidCount[1] = 0;//第1次
        invalidCount[2] = 0;//第2次
        invalidCount[3] = 1;//第3次

        for (int i = 4; i <= n; i++) {
            int aheadInvalidCount = invalidCount[i - 3] + invalidCount[i - 2] + invalidCount[i - 1];
            invalidCount[i] = aheadInvalidCount;
            count[i] = count[i - 1] * 2 - aheadInvalidCount;
        }
        return count[n];
    }


    public int getCellCount(int n) {
        if (n == 0) {
            return 1;
        }
        Queue<Node> queue = new LinkedBlockingDeque<Node>();
        int spCount = 0;
        Node oriNode = new Node();
        queue.add(oriNode);
        int lastNewAddCount = 1;
        while (spCount < n) {
            int newAddCount = 0;
            while (lastNewAddCount > 0) {
                lastNewAddCount--;
                Node curNode = queue.poll();
                curNode.division += 1;
                if (curNode.division == 3) {
                    queue.add(new Node());
                    newAddCount += 1;
                } else {
                    queue.add(curNode);
                    queue.add(new Node());
                    newAddCount += 2;
                }

            }
            lastNewAddCount = newAddCount;
            spCount++;
        }

        return queue.size();
    }

    /**
     * 深度优先搜索
     */
    public int dfsGetCellCount(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 2;
        } else {
            Stack<Node> stack = new Stack();
            Node root = new Node();
            stack.push(root);
            while (!stack.empty()) {
                Node node = stack.pop();
                node.division += 1;
                if (node.division >= 4) {
                    //
                    stack.push(new Node());
                } else {
                    stack.push(new Node());
                    stack.push(node);
                }
            }
            return stack.size();
        }
    }

    /**
     * 每小时分裂一次
     * timeH:小时数
     * selfCount:每个细胞的分裂次数，初始值为0
     * 分裂3次后就死掉
     * 其实就是计算二叉树第n层有多少个节点
     */
    public int getCellCount2(int level, int selfCount) {
        //是否自己死亡 或 不存在
        if (selfCount < 0) {
            return 0;
        } else if (selfCount >= 3) {
            return 0;
        }

        if (level == 1) {
            //节点位于最后第一层才算数，
            System.out.println("("+(level)+","+(selfCount)+")");
            return 1;
        }else if( level == 0){
            return 0;
        }
        //左+右
        System.out.println("("+(level-1)+","+(selfCount+1)+")"+" & ("+(level-1)+",0)");
        return getCellCount2(level - 1, selfCount+1) + getCellCount2(level - 1, 0);
    }

    public static class Node {
        int division;
    }

}
