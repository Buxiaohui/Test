package com.example.bxh.sayhello.otheralgorithms;

import android.text.TextUtils;
import android.util.Log;

import com.example.bxh.sayhello.Utils;
import com.example.bxh.sayhello.node.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by bxh on 11/13/16.
 */

public class TestAlgorithms {
    private final static String TAG = "TestAlgorithms";
    private static final int[][] matrix = {
            {1, 2, 4, 5, 6, 7},
            {3, 4, 7, 8, 9, 10},
            {4, 5, 8, 12, 14, 16},
            {4, 6, 9, 14, 15, 17},
    };
    /**
     * 输入一个链表，从尾到头打印链表每个节点的值。
     */
    public static ArrayList<Integer> listNodes = new ArrayList<>();

    /*************************************************/


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

    /***********************
     * start
     *******************/

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

    public static int fibonacci02(int n) {

        if (n <= 0) {
            return -1;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            int start = 3;
            int f = 1;//第1位
            int b = 1;//第2位
            while (start++ <= n) {
                //每次循环都把b和f右移一位(根据前一次结果计算新的值)，最终b就是我们要的值
                //当n=3时，只需要执行1次
                //当n=4时，移动1次以后要再移动1次
                //当n=5时，在上面的基础上再移动1次
                b = b + f;//b 变成它右边那一位
                f = b - f;//f 变成b之前的值
            }
            return b;
        }

    }

    /**
     * 二进制中1的个数
     */

    public static void getNumOfOneInBinery() {
        int targetIntValue0 = 10;//1010
        int targetIntValue1 = 11;//1011
        int targetIntValue2 = 13;//1110
        getOneNum(targetIntValue0);
        getOneNum(targetIntValue1);
        getOneNum(targetIntValue2);

    }

    public static void getOneNum(int target) {
        int origin = target;
        int count = 0;
        while (target > 0) {
            if ((target & 1) == 1) {
                count++;
            }
            target = target >> 1;
        }
        System.out.println("getNumOfOneInBinery target=" + origin + "--num = " + count);
    }

    /**
     * Given an array of integers, every element appears three times except for one. Find that single one.
     */
    public static void findSpecialNum() {
        //构造一个符合条件的数组
        //产生diffNum个不同的数字
        int diffNum = 4;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int x = (int) (Math.random() * 100);
            if (!list.contains(x)) {
                list.add(x);
            }
            if (list.size() == diffNum) {
                break;
            }
        }
        ArrayList<Integer> list0 = new ArrayList<>();
        int index = (int) (Math.random() * diffNum);
        for (int i = 0; i < diffNum; i++) {
            list0.add(list.get(i));
            if (i != index) {
                list0.add(list.get(i));
                list0.add(list.get(i));
            }
        }

        Collections.shuffle(list0);
        Integer[] array = list0.toArray(new Integer[list0.size()]);

        for (int i = 0; i < array.length; i++) {
            //打印出构造的无序数组
            System.out.println("findSpecialNum--" + array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            int count = 0;
            int x = array[i];
            for (int j = 0; j < array.length; j++) {
                if (array[j] == x) {
                    count++;
                }

            }
            if (count >= 3) {
                continue;
            } else {
                System.out.println("findSpecialNum--is=" + array[i]);
            }
        }


    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     */
    public static void testGetExponentOfNum() {

        double base = Math.random();
        int exponent = new Random().nextInt(5);
        System.out.println("testGetExponentOfNum--base=" + base + "--exponent=" + exponent);
        if (exponent == 0) {
            System.out.println("testGetExponentOfNum--0--result=" + 1);
        } else if (exponent == 1) {
            System.out.println("testGetExponentOfNum--1--result=" + base);
        } else if (exponent > 1) {
            double num = base;
            for (int i = 2; i <= exponent; i++) {
                num = num * base;
            }
            System.out.println("testGetExponentOfNum-->1--result=" + num);
        } else {
            System.out.println("testGetExponentOfNum error");
        }
        System.out.println("testGetExponentOfNum use Api result=" + Math.pow(base, exponent));
    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     */
    public static void testGetExponentOfNum01() {
        double base = Math.random() * (new Random().nextInt(1) > 0 ? 1 : -1);
        int exponent = new Random().nextInt(5) * (new Random().nextInt(1) > 0 ? 1 : -1);
        double result = 1;
        System.out.println("testGetExponentOfNum--base=" + base + "--exponent=" + exponent);
        if (exponent == 0) {
            System.out.println("testGetExponentOfNum--[=0]--result=" + 1);
        } else if (exponent > 0) {
            result = base;
            for (int i = 2; i <= exponent; i++) {
                result = result * base;
            }
            System.out.println("testGetExponentOfNum--[>0]--result=" + result);
        } else {
            result = base;
            for (int i = 2; i <= Math.abs(exponent); i++) {
                result = result * base;
            }
            result = 1 / result;
            System.out.println("testGetExponentOfNum--[<0]--result=" + result);
        }

        System.out.println("testGetExponentOfNum use Api result=" + Math.pow(base, exponent));
        System.out.println("testGetExponentOfNum==========");
    }

    /**
     * 不使用 +，-，* ,/ 计算两个整数的和
     */
    public static void testGetAdd() {
        int a = 5;//101
        int b = 7;//111

        while (b != 0) {
            int t = a ^ b;
            System.out.println("testGetAdd a^b binary=" + Integer.toBinaryString(t));
            b = (a & b) << 1;
            System.out.println("testGetAdd (a&b)<<1 binary=" + Integer.toBinaryString(b));
            a = t;
        }
        /**
         * 1.这一步是通过'异或运算'计算每一位相加后的值，不算进位。a^b
         * 101^111 即:
         * 101
         * 111
         * ----
         * 010
         * *******************
         * 2.得到进位 a&b<<1
         * 101&111 即:
         * 101
         * 111
         * ---
         * 101
         * 计算出结果是对应位相加后需要进位到高位的值，需要左移动一位，以便加到高位上去
         * 101<<1 = 1010
         * ********************
         * 3.然后把两个数字再加一起就是'和'，但是相加过程还会有进位，于是循环相加过程即第一步与第二步，直到没有进位
         * （这一步其实就是递归的思路，还是两个数字相加）
         * */

    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
     * 例如，如果输入如下矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
     * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     */

    public static void printMatrix() {
        int[][] m = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        };
        ArrayList<Integer> list = new ArrayList<>();
        int x = m[0].length;//列 数
        int y = m.length;//行 数
        int left = 0;//左边界，包含
        int top = 0;//上边界，包含
        int right = x;//右边界，不包含
        int bottom = y;//下边界，不包含
        while (left <= right && top <= bottom) {
            //打印上边的行，行号是top，范围是[0,bottom-1]
            for (int j = left; j < right; j++) {
                //行号不变，列号变，列号介于left与right之间
                System.out.println("printMatrix--" + m[top][j]);
            }
            //打印右边面的行，列号是right，范围是[0,right-1]
            for (int j = top + 1; j < bottom; j++) {
                //列号不变，行号变，由于上面打印了右上角的元素，要排除这个元素，所以top+1
                System.out.println("printMatrix--" + m[j][right - 1]);
            }

            //打印下边的行
            for (int j = right - 2; j >= left; j--) {
                //行号不变，列号变，由于前面打印了右下角的元素，打印下边元素时，排除这个元素，所以right-1-1
                System.out.println("printMatrix--" + m[bottom - 1][j]);
            }

            //打印左边的行
            for (int j = bottom - 2; j > top; j--) {
                //列号不变，行号变，由于左上角，左下角都已经打印了，需要排除这两个元素，所以bottom-1-1,j>top
                System.out.println("printMatrix--" + m[j][left]);
            }
            //打印了'第1圈'，再打印'第2圈'
            //top向下移动一行
            top++;
            //right向左移动一行
            right--;
            //bottom向上移动一行
            bottom--;
            //left向右移动一行
            left++;
        }

    }

    /*单链表翻转*/
    public static Node fanzhuan(Node node) {
        if (node == null) {
            return null;
        } else if (node.next == null) {
            return node;
        } else {
            node.next.next = node;
            return fanzhuan(node.next);
        }
    }

    /**
     * 在二维数组中查找某个数是否存在
     * 次二维数组横向与纵向都是单调递增的
     */
    public static boolean testFindValueInMatrix01(int[][] matrix, int key) {
        Log.i("testFindValueInMatrix", "testFindValueInMatrix01 key=" + key);
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;
        if (key < matrix[0][0]) {
            return false;
        } else if (key > matrix[matrix.length - 1][matrix[0].length - 1]) {
            return false;
        } else {
            for (int i = 0; i < rowCount; i++) {
                Log.i("testFindValueInMatrix", "testFindValueInMatrix01 i=" + i);
                if (key > matrix[i][columnCount - 1] || key < matrix[i][0]) {
                    Log.i("testFindValueInMatrix", "testFindValueInMatrix01 continue");
                    continue;
                } else {
                    for (int j = columnCount - 1; j >= 0; j--) {
                        if (key == matrix[i][j]) {
                            Log.i("testFindValueInMatrix", "testFindValueInMatrix01 find it " + "(" + i + "," + j + ")");
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    public static boolean testFindValueInMatrix03(int[][] matrix, int key) {
        Log.i("testFindValueInMatrix", "testFindValueInMatrix03 key=" + key);
        int i = 0, j = matrix[0].length - 1;
        int var = matrix[i][j];
        while (i < matrix.length && j >= 0) {
            if (var == key) {
                Log.i("testFindValueInMatrix", "testFindValueInMatrix03 find it " + "(" + i + "," + j + ")");
                return true;
            } else if (var < key) {
                var = matrix[++i][j];
            } else {
                var = matrix[i][--j];
            }
        }
        return false;
    }

    public static boolean testFindValueInMatrix02(
            int left,
            int right,
            int top,
            int bottom,
            int[][] matrix,
            int key) {
        Log.i("testFindValueInMatrix", "testFindValueInMatrix02 key=" + key);
        int midCol = (left + right) >> 1;
        int midRow = (top + bottom) >> 1;
        Log.i("testFindValueInMatrix", "testFindValueInMatrix02 left=" + left +
                "--right=" + right +
                "--top=" + top +
                "--bottom=" + bottom +
                "--midCol=" + midCol +
                "--midRow=" + midRow);
        Log.i("testFindValueInMatrix", "testFindValueInMatrix02 midValue=" + matrix[midRow][midCol]);
        if (left > right || top > bottom || midCol > right || midRow > bottom) {
            return false;
        }
        if (key > matrix[bottom][right]) {
            return false;
        } else if (key < matrix[top][left]) {
            return false;
        } else {

            if (key > matrix[midRow][midCol]) {
                return testFindValueInMatrix02(
                        midCol + 1,
                        right,
                        midRow + 1,
                        bottom,
                        matrix,
                        key) ||
                        testFindValueInMatrix02(
                                midCol + 1,
                                right,
                                top,
                                midRow,
                                matrix,
                                key) ||
                        testFindValueInMatrix02(
                                left,
                                midCol,
                                midRow + 1,
                                top + bottom,
                                matrix,
                                key);
            } else if (key < matrix[midRow][midCol]) {
                return testFindValueInMatrix02(
                        left,
                        midCol - 1,
                        top,
                        midRow - 1,
                        matrix,
                        key) ||
                        testFindValueInMatrix02(
                                midCol,
                                right,
                                top,
                                midRow - 1,
                                matrix,
                                key) ||
                        testFindValueInMatrix02(
                                left,
                                midCol - 1,
                                midRow,
                                bottom,
                                matrix,
                                key);
            } else {
                Log.i("testFindValueInMatrix", "testFindValueInMatrix02 find it " + "(" + (midRow) + "," + (midCol) + ")");
                return true;
            }
        }
    }

    public static void testFindValueInMatrix() {
        //testFindValueInMatrix01(matrix, 14);
        //testFindValueInMatrix03(matrix, 14);
        testFindValueInMatrix02(0, matrix[0].length - 1, 0, matrix.length - 1, matrix, 9);
    }

    /**
     * 合并两个有序（递增）数组,保持有序
     */
    public static void testMergeSequentialArray() {
        int[] b1 = {1, 3, 9, 11};
        int[] b2 = {2, 3, 8, 10, 12};
        int n1 = 0;
        int n2 = 0;
        int nFinal = 0;
        int[] finalArr = new int[b1.length + b2.length];
        while (nFinal < finalArr.length) {
            int temp2 = n2 < b2.length ? b2[n2] : -1;
            int temp1 = n1 < b1.length ? b1[n1] : temp2;
            if (temp1 < temp2) {
                finalArr[nFinal] = temp1;
                n1++;
            } else {
                finalArr[nFinal] = temp2;
                n2++;
            }
            nFinal++;
            Log.i(TAG, "nFinal is " + nFinal + " ..n1 is " + n1 + " ..n2 is " + n2);
            printArray(finalArr);
        }


    }

    public static void printArray(int[] a) {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            s.append(a[i]).append(" ");
        }
        Log.i(TAG, "finalArr=" + s);
    }

    public static void mergeSequentialArray() {


    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
     * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
     */
    public int findMoreThanHalfNum(int[] numbers) {
        int length = numbers.length;
        if (length == 0) return 0;

        int num = numbers[0], count = 1;
        for (int i = 1; i < length; i++) {
            if (numbers[i] == num) count++;
            else count--;
            if (count == 0) {
                num = numbers[i];
                count = 1;
            }
        }
        // Verifying
        count = 0;
        for (int i = 0; i < length; i++) {
            if (numbers[i] == num) count++;
        }
        if (count * 2 > length) return num;
        return 0;
    }


    /**
     * 早上视频面试了snpachat，面试只有一道题，线上写完并运行成功，题目是：
     * n个人，每个人带一个礼物，要求用所有礼物随机给每个人分配一个礼物，但不能是他自己带来的，输出一个map序列
     */
    public static class TestMap {
        HashMap<Integer, Integer> presentMap = new HashMap<Integer, Integer>();
        int[] people = {0, 1, 2, 3, 4, 5, 6};

        public void test() {
            java.util.Random random = new java.util.Random();// 定义随机类
            while (presentMap.size() != people.length) {
                int randomNum = random.nextInt(people.length);// 返回[0,6)集合中的整数，注意不包括10
                for (int x = 0; x < people.length; x++) {
                    if (!presentMap.containsKey(x) && !presentMap.containsValue(randomNum) && randomNum != x) {
                            presentMap.put(x, randomNum);
                    }
                }
            }
            Utils.showMapEntry(presentMap,"presentMap");
        }

    }


}
