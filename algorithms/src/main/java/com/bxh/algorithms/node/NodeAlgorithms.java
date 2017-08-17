package com.bxh.algorithms.node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bxh on 11/13/16.
 */

public class NodeAlgorithms {
    private final static String TAG = "TestAlgorithms";

    /**
     * 输入一个链表，从尾到头打印链表每个节点的值。
     */
    public static ArrayList<Integer> listNodes = new ArrayList<>();


    public static ArrayList<Integer> printListNode(Node listNode) {

        if (listNode != null) {
            printListNode(listNode.next);
            listNodes.add(listNode.val);
            System.out.println("printListNode listNode.val =" + listNode.val);
        }
        for (int i = 0; i < listNodes.size(); i++) {
            System.out.println("printListNode=" + listNodes.get(i) + "--size=" + listNodes.size());
        }

        return listNodes;
    }

    public static void testPrintNodeListFromTail2Head() {
        //construct data
        List<Node> list = new ArrayList<Node>();
        Node firstNode = new Node(0);
        list.add(firstNode);
        for (int i = 1; i < 20; i++) {
            Node newNode = new Node(i);
            System.out.println("testPrintNodeListFromTail2Head listSize:" + list.size() + "--i-1=" + (i - 1));
            list.get(i - 1).next = newNode;
            list.add(newNode);
        }

        printListNode(firstNode);

    }

    public static void testNodes() {
//        Node node = reverse(getNodes());
//        printNode(node);
//        Node node1 = reverse1(getNodes());
//        printNode(node1);
    }

    public static void printNode(Node node) {
        while (node != null) {
            System.out.println("printNode node.val:" + node.val);
            node = node.next;
        }
    }

    public static Node getNodes() {
        Node node = new Node(1);
        Node cur = node;
        for (int i = 2; i < 6; i++) {
            cur.next = new Node(i);
            cur = cur.next;
        }
        return node;
    }

    /*单链表翻转*/
    public static Node reverse(Node node) {
        if (node == null) {
            return null;
        } else if (node.next == null) {
            return node;
        } else {
            node.next.next = node;
            return reverse(node.next);
        }
    }

    /*单链表翻转*/
    public static Node reverse1(Node current) {
        if (current == null || current.next == null) {
            return current;
        }
        Node nextNode = current.next;
        current.next = null;
        Node reverseRest = reverse1(nextNode);
        nextNode.next = current;
        return reverseRest;

    }

    public static Node reverse2(Node current) {
        //initialization
        Node previousNode = null;
        Node nextNode = null;

        while (current != null) {
            //save the next node
            nextNode = current.next;
            //update the value of "next"
            current.next = previousNode;
            //shift the pointers
            previousNode = current;
            current = nextNode;
        }
        return previousNode;
    }
}
