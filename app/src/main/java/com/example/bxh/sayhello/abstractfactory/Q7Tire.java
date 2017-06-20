package com.example.bxh.sayhello.abstractfactory;

/**
 * Created by buxiaohui on 6/20/17.
 */

public class Q7Tire implements Itire {
    @Override
    public void buildTire() {
        System.out.println("CarFactory Q7Tire buildTire");
    }
}
