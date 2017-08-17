package com.bxh.algorithms.leetcode;

/**
 * Created by buxiaohui on 17-8-17.
 */

/**
 * There are two sorted arrays A and B of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 给定两个有序数组，长度不一定相同，一个是m、一个是n，要求给出他们合并在一起之后的数组的中位数。
 * 要求时间复杂度为O(log(m+n))，所以不可以合并数组再找中位数，否则复杂度就是O(m+n)。
 */
public class FindMedinaIn2Array {
    public static final String TAG = "FindMedinaIn2Array";

    /**
     * http://blog.csdn.net/xuyze/article/details/45198757
     * 也可以参考分析：
     * www.cnblogs.com/ganganloveu/p/4180523.html
     * 或者
     * http://www.acmerblog.com/median-of-two-sorted-arrays-5967.html
     */


    public static void test() {
        int[] a = {5, 6, 7, 8,9,10,11};
        int[] b = {1, 2, 3,4 };
        double d = Solution1.findMedianSortedArrays(a, b);
        System.out.println(TAG + "--d-->" + d);
    }

    public static class Solution1 {
        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n1 = nums1.length;
            int n2 = nums2.length;
            if ((n1 + n2) % 2 == 1) {
                //总长为奇数
                return findNumK(nums1, n1, nums2, n2, (n1 + n2) / 2 + 1);
            } else {
                //总长度为偶数
                return (findNumK(nums1, n1, nums2, n2, (n1 + n2) / 2) + findNumK(nums1, n1, nums2, n2, (n1 + n2) / 2 + 1)) / 2;
            }
        }

        public static double findNumK(int[] nums1, int n1, int[] nums2, int n2, int k) {
            System.out.println(TAG + "--findNumK--k-->" + k);
            if (n1 > n2) {//始终把长度较小的放在前面
                return findNumK(nums2, n2, nums1, n1, k);
            }
            if (n1 == 0) {//特殊情况1
                return nums2[k - 1];
            }
            if (k == 1) {//特殊情况2
                return Math.min(nums1[0], nums2[0]);
            }
            int temp1 = Math.min(k / 2, n1);//
            int temp2 = k - temp1;
            if (nums1[temp1 - 1] < nums2[temp2 - 1]) {
                return findNumK(subArray(nums1, temp1), n1 - temp1, nums2, n2, k - temp1);
            } else if (nums1[temp1 - 1] > nums2[temp2 - 1]) {
                return findNumK(nums1, n1, subArray(nums2, temp2), n2 - temp2, k - temp2);
            } else {
                return nums1[temp1 - 1];
            }
        }

        public static int[] subArray(int[] a, int b) {
            int[] temp = new int[a.length - b];
            for (int i = 0; i < a.length - b; i++) {
                temp[i] = a[b + i];
            }
            return temp;
        }
    }


}
