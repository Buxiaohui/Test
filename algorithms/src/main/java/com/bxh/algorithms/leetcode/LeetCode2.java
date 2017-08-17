package com.bxh.algorithms.leetcode;

import com.bxh.algorithms.node.Node;

/**
 * Created by buxiaohui on 17-8-17.
 */

public class LeetCode2 {
    /**
     * You are given two linked lists representing two non-negative numbers.
     * The digits are stored in reverse order and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.
     * <p>
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * 题目：给定两个代表非负数的链表，链表中的节点分别代表个十百等位数，求这个两个数的和，结果也用链表表示。
     */
    public static void test(){
        LeetCode2 leetCode2 = new LeetCode2();
        Node n1 = Node.create(new int[]{2,4,3});
        Node n2 = Node.create(new int[]{5,6,4});
        leetCode2.getTotal(n1,n2);
    }
    public Node getTotal(Node n1, Node n2) {
        Node.printNode("n1",n1);
        Node.printNode("n2",n2);
        if (n1 == null) {
            return n2;
        } else if (n2 == null) {
            return n1;
        } else {
            Node head = null;
            Node a = n1;
            Node b = n2;
            int toNext = 0;//进位
            int cur = 0;//当前位置的数值
            Node lastNode = null;
            while (a != null || b != null) {
                Node node = new Node(0);
                if (head == null) {
                    head = node;
                    lastNode = head;
                } else {
                    lastNode.next = node;
                    lastNode = lastNode.next;
                }
                int aVal = a == null ? 0 : a.val;
                int bVal = b == null ? 0 : b.val;
                cur = (aVal + bVal + toNext) % 10;
                toNext = (aVal + bVal + toNext) / 10;
                node.val = cur;
                a = a == null ? null : a.next;
                b = b == null ? null : b.next;
                Node.printNode("111head",head);
            }
            if (toNext != 0) {
                lastNode.next = new Node(toNext);
            }

            Node.printNode("head",head);
            return head;
        }
    }
}
