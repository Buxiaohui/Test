package com.example.bxh.sayhello;

/**
 * Created by bxh on 3/15/17.
 */
/**
 * 汉诺塔
 * */
public class Hanoi {
    /**
     * 递归实现
     * */
    private void move(char origin,char destination){
        System.out.println("from---"+origin+"--->"+destination);
    }

    private void hanoi(int n, char origin, char assist, char destination){
        if(n == 1){
            move(origin,destination);
        }else {
            hanoi(n - 1, origin, destination, assist);
            move(origin, destination);
            hanoi(n - 1, assist, origin, destination);
        }

    }

}
