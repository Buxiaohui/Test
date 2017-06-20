package com.example.bxh.sayhello.abstractfactory;

/**
 * Created by buxiaohui on 6/20/17.
 */

public class Q3Tire implements Itire {
    @Override
    public void buildTire() {
        System.out.println("CarFactory Q3Tire buildTire");
    }
}
