package com.example.bxh.sayhello;

/**
 * Created by bxh on 12/20/16.
 */

public class Node {
    int val;
    Node next;

    public Node() {

    }
    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}