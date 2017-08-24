package com.example.bxh.sayhello.data;

/**
 * Created by buxiaohui on 17-8-22.
 */

public class DataTest {
    public static void test() {
//        UserStack stack = new UserStack(11);
//        System.out.println("---push---");
//        for (int i = 0; i < 23; i++) {
//            stack.push(i);
//        }
//        System.out.println(stack);
//        System.out.println("---pop---");
//        for (int i = 0; i < 50; i++) {
//            stack.pop();
//        }
//        System.out.println(stack);
        UserQueue queue = new UserQueue();
        System.out.println("UserQueue---add---");
        for (int i = 0; i < 23; i++) {
            queue.add(i);
        }
        System.out.println("UserQueue,"+queue);
        System.out.println("UserQueue---remove---");
        for (int i = 0; i < 50; i++) {
            queue.remove();
        }
        System.out.println("UserQueue,"+queue);
    }
}
