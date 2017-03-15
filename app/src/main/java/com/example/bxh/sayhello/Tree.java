package com.example.bxh.sayhello;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by bxh on 11/30/16.
 * 树的相关学习
 */

public class Tree {
    /*************************************************/
    /*********************** start *******************/
    /*************************************************/
    public static int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static String[] pre = {"G", "D", "A", "F", "E", "M", "H", "Z"};
    private static String[] in = {"A", "D", "E", "F", "G", "H", "M", "Z"};
    private static String[] post = {"A", "E", "F", "D", "H", "Z", "M", "G"};

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
                list.get(parentNodeIndex).left = list.get(2 * parentNodeIndex + 1);
            }
            if (list.get(2 * parentNodeIndex + 2) != null) {
                list.get(parentNodeIndex).right = list.get(2 * parentNodeIndex + 2);
            }
        }
        //the last parentNode
        int lastParentNodeIndex = array.length / 2 - 1;
        if (list.get(lastParentNodeIndex * 2 + 1) != null) {
            list.get(lastParentNodeIndex).left = list.get(lastParentNodeIndex * 2 + 1);
        }
        if (array.length % 2 != 0) {
            if (list.get(lastParentNodeIndex * 2 + 2) != null) {
                list.get(lastParentNodeIndex).right = list.get(lastParentNodeIndex * 2 + 2);
            }
        }

        return list;

    }

    public static void printNodeValue(Node node) {
        System.out.println("printNodeValue--node.val=" + (node.valInt == -1 ? node.valStr : node.valInt));
    }

    public static void test() {
        Node root = getRoot();
//        int num = getNodeNum(root);
//        int deepNumum = getNodeDeepNum(root);
//        System.out.println("printNodeValue--num=" + num);
//        System.out.println("printNodeValue--deepNumum=" + deepNumum);

//        specialTraversal(root);
        boolean isAvl1 = isAvl(root);
        boolean isAvl2 = isAvl(getTestAvlNode2());
        boolean isAvl3 = isAvl(getTestAvlNode3());
        System.out.println("printNodeValue--isAvl1=" + isAvl1);
        System.out.println("printNodeValue--isAvl2=" + isAvl2);
        System.out.println("printNodeValue--isAvl3=" + isAvl3);

    }

    /**
     * 递归实现前序遍历
     */
    public static void leftTraversal(Node p) {
        if (p != null) {
            printNodeValue(p);
            leftTraversal(p.getLeft());
            leftTraversal(p.getRight());
        }
    }

    public static void midTraversal(Node p) {
        if (p != null) {
            midTraversal(p.getLeft());
            printNodeValue(p);
            midTraversal(p.getRight());
        }
    }

    public static void rightTraversal(Node p) {
        if (p != null) {
            rightTraversal(p.getLeft());
            rightTraversal(p.getRight());
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

    public static Node initTree(String[] preOrder, String[] inOrder) {
        Node root = initTree(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
        return root;
    }

    public static Node initTree(String[] preOrder, int start1, int end1, String[] inOrder, int start2, int end2) {
        //System.out.println("printNodeValue start1="+start1+"--end1="+end1+"--start2="+start2+"--end2="+end2);
        if (start1 > end1 || start2 > end2) {
            //System.out.println("printNodeValue null");
            return null;
        }
        //root节点必然是'前序遍历'结果的第一个
        String rootData = preOrder[start1];
        //System.out.println("printNodeValue rootData="+rootData);
        Node head = new Node(rootData);
        //找到根节点在'中序遍历'中所在位置
        int rootIndex = findIndexInArray(inOrder, rootData, start2, end2);
        //构建左子树
        //左子树的 前序遍历 preOrder,从 start1+1,到 start1+rootIndex-start2
        //左子树的 中序遍历 inOrder,从 start2,到 rootIndex-1
        Node left = initTree(preOrder, start1 + 1, start1 + rootIndex - start2, inOrder, start2, rootIndex - 1);
        //构建右子树
        Node right = initTree(preOrder, start1 + rootIndex - start2 + 1, end1, inOrder, rootIndex + 1, end2);
        head.left = left;
        head.right = right;
        return head;
    }

    public static int findIndexInArray(String[] a, String x, int begin, int end) {
        for (int i = begin; i <= end; i++) {
            if (a[i].equalsIgnoreCase(x)) {
                return i;
            }
        }
        return -1;
    }

    public static Node getRootPrePost(String[] pre, String[] post) {
        return null;
    }

    public static Node getRootPreIn(String[] pre, String[] in) {
        if (pre == null || in == null) {
            return null;
        }
        Node root = new Node(pre[0]);
        if (root == null) {
            return null;
        }
        int len = pre.length;
        if (len == 0) {
            return null;
        }
        if (len == 1) {
            root.left = null;
            root.right = null;
            return root;
        }
        int index = getIndex(in, root.valStr);
        if (index <= 0) {
            //中序遍历结果，根节点是第一个，则说明没有左子树
            root.left = null;
        } else {
            String[] preChild = new String[index];
            String[] inChild = new String[index];
            System.arraycopy(pre, 1, preChild, 0, index);
            System.arraycopy(in, 0, inChild, 0, index);
            root.left = getRootPreIn(preChild, inChild);
        }

        if ((len - (index + 1)) <= 0) {
            //中序遍历结果，根节点是最后一个，则说明没有右子树
            root.right = null;
        } else {
            //index+1 ---> 左子树与根节点加一起的长度
            //len-(index+1) ---> 右子树的长度
            String[] preChild = new String[len - (index + 1)];
            String[] inChild = new String[len - (index + 1)];
            System.arraycopy(pre, index + 1, preChild, 0, len - (index + 1));
            System.arraycopy(in, index + 1, inChild, 0, len - (index + 1));
            root.right = getRootPreIn(preChild, inChild);
        }
        return root;
    }

    public static void printSingle(Node root) {
        System.out.println("printNodeValue=====node cal="+root.valStr);
    }
    public static void print(Node root) {
        System.out.println("printNodeValue=====pre=====");
        leftTraversal(root);
        System.out.println("printNodeValue======in====");
        midTraversal(root);
        System.out.println("printNodeValue======post====");
        rightTraversal(root);
    }

    public static int getIndex(String[] array, String element) {
        if (array == null || TextUtils.isEmpty(element)) {
            return -1;
        }
        for (int i = 0; i < array.length; i++) {
            if (element.equalsIgnoreCase(array[i])) {
                return i;
            }
        }
        return -1;
    }

    public static Node getRootPreIn() {
        return null;
    }

    public static Node getRoot() {
        Node root = getRootPreIn(pre, in);
        //return getRoot(pre, in);
        //print(root);
        return root;
    }

    private static Node getRoot(String[] pre, String[] in) {
        Node root = initTree(pre, in);
        //test print
        leftTraversal(root);
        System.out.println("printNodeValue should be=\"G\", \"D\", \"A\", \"F\", \"E\", \"M\", \"H\", \"Z\"");
        midTraversal(root);
        System.out.println("printNodeValue should in=\"A\", \"D\", \"E\", \"F\", \"G\", \"H\", \"M\", \"Z\"");
        rightTraversal(root);
        return root;
    }

    /**
     * 获得节点数目
     */
    private static int getNodeNum(Node node) {
        if (node == null) {
            return 0;
        }
        System.out.println("printNodeValue--getNodeNum node=" +node.valStr);
        return getNodeNum(node.left) + getNodeNum(node.right) + 1;
    }
    /**
     * 获得深度
     * （1）如果二叉树为空，二叉树的深度为0
     * （2）如果二叉树不为空，二叉树的深度 = max(左子树深度， 右子树深度) + 1
     */
    private static int getNodeDeepNum(Node node) {
        if (node == null) {
            return 0;
        }
        int leftDeepNum = getNodeDeepNum(node.left)+1;
        int rightDeepNum = getNodeDeepNum(node.right)+1;

        return Math.max(leftDeepNum,rightDeepNum);
    }

    /**
     * 求二叉树第K层的节点个数
     * */
     private static int getNodeNumAtSpecialLayer(int k,Node root){
         if(root == null){
             return 0;
         }
         if(k < 1){
             return 0;
         }
         if(k == 1){
             return 1;
         }
         int leftNodeNum = getNodeNumAtSpecialLayer(k-1,root.left);
         int rightNodeNum = getNodeNumAtSpecialLayer(k-1,root.right);
         return leftNodeNum + rightNodeNum;
     }

    /**
     * null  0 ；
     * not null ，but no child 1；
     * not null，have child，return (Num of left child tree) + (Num of right child tree)
     *
     * */
    private static int calculateNoChildNodeNum(Node node){
        if(node == null){
            return 0;
        }
        if(node.left == null && node.right == null){
            return 1;
        }
        int leftNum = calculateNoChildNodeNum(node.left);
        int rightNum = calculateNoChildNodeNum(node.right);
        return leftNum+rightNum;
    }

    /**
     * to judge whether one tree is same as another one
     * */
    private static boolean isSameStructure(Node node0,Node node1){
        if(node0 == null && node1 == null){
            return true;
        }
        if(node0 == null &&  node1 != null){
            return false;
        }
        if(node0 != null &&  node1 == null){
            return false;
        }
        return  isSameStructure(node0.left,node1.left) && isSameStructure(node0.right,node1.right);
    }
    /**
     * 分层遍历二叉树（按层次从上往下，从左往右）
     * */
    private static void specialTraversal(Node node){
        if(node == null){
            System.out.print("--null--");
            return;
        }

        Queue<Node> queue = new LinkedBlockingDeque<>();
       if(node != null){
           queue.add(node);

       }
        while (!queue.isEmpty()){
            Node node1 = queue.poll();
            printNodeValue(node1);
           if(node1.left !=null){
               queue.add(node1.left);
           }
           if(node1.right !=null){
               queue.add(node1.right);
           }
       }
    }

    /**
     * 已知'前序遍历' 和 '中序遍历'  的结果
     * 建树，并打印出来
     * PreOrder:         GDAFEMHZ
     * InOrder:          ADEFGHMZ
     * PostOrder:       AEFDHZMG
     * G
     * <p>
     * D                  M
     * A          (D)F    (M)H     Z
     * (F)E
     */
    private Node initRoot() {
        Node root = new Node("G");
        root.left = new Node("D");
        root.left.left = new Node("A");
        root.left.right = new Node("F");
        root.left.right.left = new Node("E");

        root.right = new Node("M");
        root.right.left = new Node("H");
        root.left.right = new Node("Z");
        return root;
    }

    private static Node getTestAvlNode2(){
        Node node = new Node(1);
        node.left = new Node(2);
        node.left.left = new Node(3);
        return node;
    }
    private static Node getTestAvlNode3(){
        Node node = new Node(1);
        node.left = new Node(2);
        return node;
    }

    /**
     * to judge whether a tree is AVL tree
     * (1）如果二叉树为空，返回真
     *（2）如果二叉树不为空，如果左子树和右子树都是AVL树并且左子树和右子树高度相差不大于1，返回真，其他返回假
     * */
    private static boolean isAvl(Node node){
        if(node == null){
            return true;
        }
        boolean isLeft = isAvl(node.left);
        boolean isRight = isAvl(node.right);
        int deepLeft  = getNodeDeepNum(node.left);
        int deepRight  = getNodeDeepNum(node.right);
        if(isLeft && isRight && Math.abs(deepLeft - deepRight) <=1){
            return true;
        }
        return false;
    }

    public static class Node {
        int valInt = -1;
        String valStr;
        Node left;
        Node right;

        public Node(int self) {
            this.valInt = self;
        }

        public Node() {
        }

        public Node(String self) {
            this.valStr = self;
        }

        public String getValStr() {
            return valStr;
        }

        public void setValStr(String valStr) {
            this.valStr = valStr;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getValInt() {
            return valInt;
        }

        public void setValInt(int valInt) {
            this.valInt = valInt;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }
    }

}
