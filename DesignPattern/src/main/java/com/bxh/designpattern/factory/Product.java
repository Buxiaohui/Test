package com.bxh.designpattern.factory;

/**
 * Created by buxiaohui on 6/19/17.
 */

public class Product<T> {
    public T t;
    public <F extends Factory<T>> Product(Factory<T> factory){
        t = factory.buildProduct();
    }
}
