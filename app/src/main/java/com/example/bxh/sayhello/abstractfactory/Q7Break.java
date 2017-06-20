package com.example.bxh.sayhello.abstractfactory;

/**
 * Created by buxiaohui on 6/20/17.
 */

public class Q7Break implements IBreak {
    @Override
    public void buildBreak() {
        System.out.println("CarFactory Q7Break buildBreak");
    }
}
