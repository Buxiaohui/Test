package com.example.bxh.sayhello;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bxh on 11/13/16.
 */

public class TestAlgorithms {
    private final static String TAG = "TestAlgorithms";
    /**
     * 输入一个链表，从尾到头打印链表每个节点的值。
     */
    public static ArrayList<Integer> listNodes = new ArrayList<>();

    public static double test02(int N) {
        Log.d(TAG, "test01 N=" + N);
        if (N - 1 >= 0) {
            return Math.log(N) + test02(N - 1);
        }
        return 0;
    }

    public static boolean isInside01(int target, int[][] array) {
        //final long startT = System.currentTimeMillis();
        final long startT = System.nanoTime();
        System.out.println("isInside01 start:" + startT);
        if (array == null) {
            return false;
        } else if (!(array.length > 0)) {
            return false;
        } else {
            int tx = 0;
            int ty = array.length - 1;
            while (tx >= 0 && tx < array[0].length && ty >= 0 && ty < array.length) {
                if (target < array[tx][ty]) {
                    ty--;
                } else if (target > array[tx][ty]) {
                    tx++;
                } else {
                    System.out.println("isInside01 total time:" + (System.nanoTime() - startT));
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isInside02(int target, int[][] array) {
        long startT = System.nanoTime();
        System.out.println("isInside02 start:" + startT);
        if (array == null) {
            return false;
        } else if (!(array.length > 0)) {
            return false;
        } else {
            for (int x = array.length - 1; x >= 0; x--) {
                for (int y = array[0].length - 1; y >= 0; y--) {
                    int temp = array[x][y];
                    if (target == temp) {
                        System.out.println("isInside02 total time:" + (System.nanoTime() - startT));

                        return true;
                    }
                }
            }
        }
        return false;
    }
    /*************************************************/
    /*********************** start *******************/
    /*************************************************/

    public static ArrayList<Integer> printListNode(ListNode listNode) {

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
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode firstNode = new ListNode(0);
        list.add(firstNode);
        for (int i = 1; i < 20; i++) {
            ListNode newNode = new ListNode(i);
            System.out.println("testPrintNodeListFromTail2Head listSize:" + list.size() + "--i-1=" + (i - 1));
            list.get(i - 1).next = newNode;
            list.add(newNode);
        }

        printListNode(firstNode);

    }

    /*************************************************/

    public static String string2String() {
        final String s = "aasssssdddddfffggh";
        LinkedHashMap<String, Integer> m = new LinkedHashMap<String, Integer>();
        Character c = null;
        int count = 0;
        CharSequence ss = s;
        for (int i = 0; i < s.length(); i++) {
            System.out.println("string2String i=" + i + "---s=" + s);
            s.charAt(2);
            if (s.charAt(i) == c) {
                count++;
            } else {
                m.put(c.toString(), count);
                c = s.charAt(i);
                count = 0;
            }

        }
        StringBuffer result = new StringBuffer();
        Iterator<Map.Entry<String, Integer>> i = m.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String, Integer> e = i.next();
            String key = e.getKey();
            int value = e.getValue();
            if (TextUtils.isEmpty(key)) {
                continue;
            }
            result.append(key).append(value);
        }
        String re = result.toString();
        System.out.println("string2String:" + re);
        return re;
    }

    public static int fibonacci(int n) {
        if (n <= 0) {
            return -1;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }

    }
    /*************************************************/
    /***********************  end ********************/

    public void test01() {
        Log.d(TAG, "onQueryTextSubmit Integer.toBinaryString=" + Integer.toBinaryString(10));
        String s = "";
        for (int n = 10; n > 0; n /= 2) {
            s = (n % 2) + s;
            Log.d(TAG, "onQueryTextSubmit s=" + s);
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
