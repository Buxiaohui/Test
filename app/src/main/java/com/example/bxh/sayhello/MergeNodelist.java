package com.example.bxh.sayhello;

import android.os.StrictMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bxh on 12/20/16.
 */

public class MergeNodelist {

    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则
     */

    public static void testMergeList() {
        //构造listA & listB
        Node nodeA = buildNode();
        Node nodeB = buildNode();

        //Node node = testMergeList(nodeA, nodeB);
        Node node2 = testMergeList3(nodeA, nodeB);
        printListNode(node2);
        //Node node1 = reSortArray2List(nodeA,nodeB);
        //printListNode(node);
        //printListNode(node1);
    }

    /**
     * 递归
     */
    public static Node testMergeList(Node nodeA, Node nodeB) {
        Node node = null;
        if (nodeA == null) {
            return nodeB;
        }
        if (nodeB == null) {
            return nodeA;
        }
        if (nodeA.val < nodeB.val) {
            node = nodeA;
            node.next = testMergeList(nodeA.next, nodeB);
        } else {
            node = nodeB;
            node.next = testMergeList(nodeA, nodeB.next);
        }
        return node;
    }
    public static Node testMergeList3(Node nodeA, Node nodeB) {
        if (nodeA == null) {
            return nodeB;
        }
        if (nodeB == null) {
            return nodeA;
        }
        if (nodeA.val < nodeB.val) {
            nodeA.next = testMergeList(nodeA.next, nodeB);
            return nodeA;
        } else {
            nodeB.next = testMergeList(nodeA, nodeB.next);
            return nodeB;
        }
    }
    private static Node reSortArray2List(Node nodeA,Node nodeB){
        if(nodeA == null){
            return nodeB;
        }
        if(nodeB == null){
            return nodeB;
        }

        if(nodeA.val < nodeB.val){
            nodeA.next = (reSortArray2List(nodeA.next, nodeB));
            return nodeA;
        }else{
            nodeB.next = (reSortArray2List(nodeA, nodeB.next));
            return nodeB;
        }

    }

    private static Node buildNode() {
        System.out.println("MergeNodelist -----buildNode--start--");
        int end = 5 + new Random().nextInt(10);
        int start = new Random().nextInt(5);
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < end - start; i++) {
            Node next = new Node(i + start + new Random().nextInt(2));
            System.out.println("MergeNodelist--buildNode--" + next.val);
            if (list.size() == 0) {
                list.add(next);
            } else {
                list.get(i - 1).next = next;
                list.add(next);
            }
        }
        System.out.println("MergeNodelist-----buildNode--end--");
        return list.get(0);
    }

    public static void printListNode(Node node) {
        if (node != null) {
            System.out.println("MergeNodelist---printListNode node.val =" + node.val);
            printListNode(node.next);
        }
    }

    /**
     * 非递归
     */
    public Node testMergeList02(Node nodeA, Node nodeB) {
        Node firstNode = null;
        Node currentNode = null;
        if (nodeA == null) {
            return nodeB;
        }
        if (nodeB == null) {
            return nodeA;
        }

        while (nodeA != null && nodeB != null) {
            if (nodeA.val < nodeB.val) {
                if(firstNode==null){
                    currentNode = nodeA;
                    firstNode = nodeA;
                }else{
                    currentNode.next = nodeA;
                    currentNode = currentNode.next;
                }
                nodeA = nodeA.next;

            } else {
                if(firstNode==null){
                    firstNode = nodeB;
                    currentNode = nodeB;
                }else{
                    currentNode.next = nodeB;
                    currentNode = currentNode.next;
                }
                nodeB = nodeB.next;
            }
        }

        return  firstNode;
    }

    public Node testMergeList03(Node nodeA, Node nodeB) {
        Node firstNode = null;//头节点
        Node currentNode = null;//游标指向前一个节点
        if (nodeA == null) {
            return nodeB;
        }
        if (nodeB == null) {
            return nodeA;
        }
        //游标A 游标B 作对比
        while (nodeA != null && nodeB != null) {
             if(nodeA.val < nodeB.val){
                 if(firstNode == null){
                     //初始化 头
                     firstNode = nodeA;
                     //游标指向前一个节点
                     currentNode = nodeA;
                 }else{
                     //两个游标 当前小的下一个节点
                     currentNode.next = nodeA;
                     currentNode = currentNode.next;
                 }
                 //更新游标A
                 nodeA = nodeA.next;
             }else{
                 //雷同
             }
        }

        return  firstNode;
    }

}
