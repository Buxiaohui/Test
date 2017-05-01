package com.example.bxh.sayhello.node;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

}
