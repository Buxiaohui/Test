package com.bxh.designpattern.abstractfactory;

/**
 * Created by buxiaohui on 6/20/17.
 */

public class AbstractFactoryTestClient {
    public static void test(){
        Q3Factory q3Factory = new Q3Factory();
        q3Factory.buildBreak();
        q3Factory.buildTire();
        q3Factory.buildEngine();

        Q7Factory Q7Factory = new Q7Factory();
        Q7Factory.buildBreak();
        Q7Factory.buildTire();
        Q7Factory.buildEngine();
        /**
         * 06-20 09:47:13.451 24411-24411/? I/System.out: CarFactory Q3Break buildBreak
         * 06-20 09:47:13.451 24411-24411/? I/System.out: CarFactory Q3Tire buildTire
         * 06-20 09:47:13.451 24411-24411/? I/System.out: CarFactory Q7Break buildBreak
         * 06-20 09:47:13.451 24411-24411/? I/System.out: CarFactory Q7Tire buildTire
         * */
    }
}
