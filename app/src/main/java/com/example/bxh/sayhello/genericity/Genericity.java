package com.example.bxh.sayhello.genericity;

/**
 * Created by buxiaohui on 6/19/17.
 */

public class Genericity {
    public Genericity( ) {

    }
    String name;
    public Genericity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    protected void haha(){

    }
}
