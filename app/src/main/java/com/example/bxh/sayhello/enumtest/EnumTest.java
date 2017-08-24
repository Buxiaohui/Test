package com.example.bxh.sayhello.enumtest;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by buxiaohui on 17-8-22.
 */

public class EnumTest {
    public static final int TYPE1 = 1 << 0;
    public static final int TYPE2 = 1 << 1;
    public static final int TYPE3 = 1 << 2;
    public static final int TYPE4 = 1 << 3;
    private static final String TAG = "EnumTest";
    public int type;

    public static void test() {
        EnumTest enumTest = new EnumTest();
        enumTest.setType(TYPE1 | TYPE2);
        enumTest.setType(TYPE1 | TYPE3);
        enumTest.setType(TYPE1 | TYPE4);

        enumTest.setType(EnumSet.of(Type.A, Type.B));
/**
 *08-22 09:26:46.175 18951-18951/? I/System.out: EnumTest int type=3
 *08-22 09:26:46.175 18951-18951/? I/System.out: EnumTest int type=5
 *08-22 09:26:46.175 18951-18951/? I/System.out: EnumTest int type=9
 *08-22 09:26:46.176 18951-18951/? I/System.out: EnumTest emum type=[A, B]
 */
    }

    private void setType(int type) {
        this.type = type;
        System.out.println(TAG + " int type=" + type);
    }

    private void setType(Set<Type> types) {
        System.out.println(TAG + " emum type=" + types);
    }

    public enum Type {
        A(1), B(2), C(3), D(4);
        int val;

        Type(int val) {
            this.val = val;
        }
    }

}
