package com.example.bxh.sayhello;

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

        Node node = testMergeList(nodeA, nodeB);
        printListNode(node);
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
    public static void testMergeList02() {
        //构造listA & listB
        Node nodeA = buildNode();
        Node nodeB = buildNode();
        Node node = null;
        while (nodeA != null && nodeB != null) {
            if (nodeA.val < nodeB.val) {
                node = nodeA;
                node.next = nodeB;
                nodeB = nodeA.next;

                nodeA.next = nodeB;
            } else {
                node = nodeB;
                nodeA.next = nodeB.next;
                nodeB.next = nodeA;
            }
        }

    }

}
