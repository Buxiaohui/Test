package com.bxh.designpattern.abstractfactory;

/**
 * Created by buxiaohui on 6/20/17.
 */

public class Q3Break implements IBreak {
    @Override
    public void buildBreak() {
        System.out.println("CarFactory Q3Break buildBreak");
    }
}
