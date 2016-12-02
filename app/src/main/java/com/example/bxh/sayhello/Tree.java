package com.example.bxh.sayhello;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by bxh on 11/30/16.
 * 树的相关学习
 */

public class Tree {
    /*************************************************/
    /*********************** start *******************/
    /*************************************************/
    public static int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public Tree() {

    }

    /**
     * 把数组变成二叉树
     */
    public static ArrayList<Node> sortF() {
        ArrayList<Node> list = new ArrayList<Node>();
        for (int i = 0; i < array.length; i++) {
            list.add(new Node(array[i]));
        }

        for (int parentNodeIndex = 0; parentNodeIndex < array.length / 2 - 1; parentNodeIndex++) {
            if (list.get(2 * parentNodeIndex + 1) != null) {
                list.get(parentNodeIndex).leftChildNode = list.get(2 * parentNodeIndex + 1);
            }
            if (list.get(2 * parentNodeIndex + 2) != null) {
                list.get(parentNodeIndex).rightChildNode = list.get(2 * parentNodeIndex + 2);
            }
        }
        //the last parentNode
        int lastParentNodeIndex = array.length / 2 - 1;
        if (list.get(lastParentNodeIndex * 2 + 1) != null) {
            list.get(lastParentNodeIndex).leftChildNode = list.get(lastParentNodeIndex * 2 + 1);
        }
        if (array.length % 2 != 0) {
            if (list.get(lastParentNodeIndex * 2 + 2) != null) {
                list.get(lastParentNodeIndex).rightChildNode = list.get(lastParentNodeIndex * 2 + 2);
            }
        }

        return list;

    }

    public static void printNodeValue(Node node) {
        System.out.println("printNodeValue--node.selfValue=" + node.selfValue);
    }

    public static void test() {
        ArrayList<Node> list = sortF();
        System.out.println("printNodeValue=====left=====");
        leftTraversal(list.get(0));
        System.out.println("printNodeValue======mid====");
        midTraversal(list.get(0));
        System.out.println("printNodeValue======right====");
        rightTraversal(list.get(0));
    }

    /**
     * 递归实现前序遍历
     */
    public static void leftTraversal(Node p) {
        if (p != null) {
            printNodeValue(p);
            leftTraversal(p.getLeftChildNode());
            leftTraversal(p.getRightChildNode());
        }
    }


    public static void midTraversal(Node p) {
        if (p != null) {
            midTraversal(p.getLeftChildNode());
            printNodeValue(p);
            midTraversal(p.getRightChildNode());
        }
    }

    public static void rightTraversal(Node p) {
        if (p != null) {
            rightTraversal(p.getLeftChildNode());
            rightTraversal(p.getRightChildNode());
            printNodeValue(p);
        }
    }

    public static int[] initData() {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = i;
        }
        return a;
    }

    public static class Node {
        int selfValue;
        Node leftChildNode;
        Node rightChildNode;

        public Node(int self) {
            this.selfValue = self;
        }

        public Node getRightChildNode() {
            return rightChildNode;
        }

        public void setRightChildNode(Node rightChildNode) {
            this.rightChildNode = rightChildNode;
        }

        public int getSelfValue() {
            return selfValue;
        }

        public void setSelfValue(int selfValue) {
            this.selfValue = selfValue;
        }

        public Node getLeftChildNode() {
            return leftChildNode;
        }

        public void setLeftChildNode(Node leftChildNode) {
            this.leftChildNode = leftChildNode;
        }
    }
}
