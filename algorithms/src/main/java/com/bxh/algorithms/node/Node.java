package com.bxh.algorithms.node;

/**
 * Created by bxh on 12/20/16.
 */

public class Node {
    public int val;
    public Node next;

    public Node() {

    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public static void printNode(Node node) {
        while (node != null) {
            System.out.println("printNode node.val:" + node.val);
            node = node.next;
        }
    }

    public static Node create(int... args) {
        if (args == null || args.length < 1) {
            return new Node(-1);
        }
        Node head = null;
        Node cur = null;
        for (int i = 0; i < args.length; i++) {
            if (head == null) {
                head = new Node(args[i]);
                cur = head;
            } else {
                cur.next = new Node(args[i]);
                cur = cur.next;
            }
        }
        return head;
    }

    public static void printNode(String tag, Node node) {
        StringBuffer s = null;
        while (node != null) {
            if (s == null) {
                s = new StringBuffer("");
            }
            s.append(node.val);
            node = node.next;
            if(node != null){
                s.append(" ,");
            }

        }
        System.out.println(tag + "  printNode nodes:" + s.toString());
    }
}