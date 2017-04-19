package com.example.bxh.sayhello;

/**
 * Created by bxh on 11/10/16.
 */

public class ChildClass extends BaseClass {
    // 静态变量
    public static String staticField = "classTest ChildClass 静态变量";

    // 静态初始化块
    static {
        staticField = "classTest ChildClass 静态变量被改变";
        System.out.println(staticField);
        System.out.println("classTest ChildClass 静态初始化块");
    }

    // 变量
    public String field = "classTest ChildClass 变量";

    // 初始化块
    {
        System.out.println(field);
        System.out.println("classTest ChildClass 初始化块");
    }

    // 构造器
    public ChildClass() {
        System.out.println("classTest ChildClass 构造器");
    }
}
