package com.example.bxh.sayhello.abstractfactory;

/**
 * Created by buxiaohui on 6/20/17.
 */

public class Q3Factory implements CarFactory{
    @Override
    public void buildTire() {
       new Q3Tire().buildTire();
    }

    @Override
    public void buildEngine() {

    }

    @Override
    public void buildBreak() {
        new Q3Break().buildBreak();
    }
}
