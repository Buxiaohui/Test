package com.example.bxh.sayhello;

/**
 * Created by bxh on 11/10/16.
 * 类顺序初始化
 */

public class BaseClass {
    // 静态变量
    public static String staticField = "classTest BaseClass 静态变量";

    // 静态初始化块
    static {
        System.out.println(staticField);
        System.out.println("classTest BaseClass 静态初始化块");
    }

    // 变量
    public String field = "classTest BaseClass 变量";

    // 初始化块
    {
        System.out.println(field);
        System.out.println("classTest BaseClass 初始化块");
    }

    // 构造器
    public  BaseClass() {
        System.out.println("classTest BaseClass 构造器");
    }
}
