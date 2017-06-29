package com.bxh.designpattern.abstractfactory;

/**
 * Created by buxiaohui on 6/20/17.
 */

public class Q7Factory implements CarFactory{
    @Override
    public void buildTire() {
         new Q7Tire().buildTire();
    }

    @Override
    public void buildEngine() {

    }

    @Override
    public void buildBreak() {
       new Q7Break().buildBreak();
    }
}
