package com.example.bxh.sayhello.sometest;

import java.util.Arrays;

/**
 * Created by buxiaohui on 7/1/17.
 */

public class ObjectCloneTestClient {
    final static String TAG = "ObjectCloneTestClient";
    public static void test(){
        System.out.println(TAG+"--------test int clone()----");
        int[] a ={1,2,3,4,5};
        int[] b = a.clone();
        System.out.println(TAG+"--a:"+ Arrays.toString(a));
        System.out.println(TAG+"--b:"+ Arrays.toString(a));
        b[1]=10;
        System.out.println(TAG+"--after chang b[1]");
        System.out.println(TAG+"--a:"+ Arrays.toString(a));
        System.out.println(TAG+"--b:"+ Arrays.toString(b));
        System.out.println(TAG+"--------test int System.arraycopy----");
        int[] c = {1,2,3,4,5};
        int[] d = new int[5];
        System.arraycopy(c,0,d,0,c.length);
        System.out.println(TAG+"--after  System.arraycopy ");
        System.out.println(TAG+"--c:"+ Arrays.toString(c));
        System.out.println(TAG+"--d:"+ Arrays.toString(d));
        System.out.println(TAG+"--after  change b[1]");
        d[1] = 10;
        System.out.println(TAG+"--c:"+ Arrays.toString(c));
        System.out.println(TAG+"--d:"+ Arrays.toString(d));
        /*******************************************************/
        /*******************************************************/
        /*******************************************************/
        System.out.println(TAG+"--------test object clone()----");
        N[] e ={new N("1111"),new N("2222"),new N("3333")};
        N[] f = e.clone();
        System.out.println(TAG+"--e:"+ Arrays.toString(e));
        System.out.println(TAG+"--f:"+ Arrays.toString(f));
        f[1].name = "4444";
        System.out.println(TAG+"--after chang f[1]");
        System.out.println(TAG+"--e:"+ Arrays.toString(e));
        System.out.println(TAG+"--f:"+ Arrays.toString(f));
        System.out.println(TAG+"--------test int System.arraycopy----");
        N[] g = {new N("1111"),new N("2222"),new N("3333")};
        N[] h = new N[3];
        System.arraycopy(g,0,h,0,g.length);
        System.out.println(TAG+"--after  System.arraycopy ");
        System.out.println(TAG+"--g:"+ Arrays.toString(g));
        System.out.println(TAG+"--h:"+ Arrays.toString(h));
        System.out.println(TAG+"--after  change h[1]");
        h[1].name = "4444";
        System.out.println(TAG+"--g:"+ Arrays.toString(g));
        System.out.println(TAG+"--h:"+ Arrays.toString(h));
    }

    public static class N {
        public String name ;
        public N(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
