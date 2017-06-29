package com.bxh.designpattern.abstractfactory;

/**
 * Created by buxiaohui on 6/20/17.
 */

public interface CarFactory {
    public void buildTire();
    public void buildEngine();
    public void buildBreak();
}
