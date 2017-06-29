package com.bxh.algorithms.OtherAlgorithms;

import java.util.Stack;

/**
 * Created by bxh on 12/2/16.
 */

public class StackToQueue {
    private static final String TAG  ="StackToQueue";
    public Stack<TestBean> fStack = new Stack<>();
    public Stack<TestBean> sStack = new Stack<>();


    public void insert(TestBean bean){
        fStack.push(bean);
    }

    public TestBean delete(){
        //just print data
        for (int z = 0; z < fStack.size(); z++) {
            //System.out.println(TAG+"--print current fStack--"+fStack.get(z).name);
        }
        //clear sStack
        for (int i = 0; i < sStack.size(); i++) {
            sStack.pop();
        }
        //put data to sStack
        int sizeOrigin = fStack.size();
        for (int i = 0; i < sizeOrigin; i++) {
            sStack.push(fStack.pop());
        }
        //just print data
        for (int z = 0; z < sStack.size(); z++) {
            //System.out.println(TAG+"--to sStack--"+sStack.get(z).name);
        }

        TestBean t = sStack.pop();
        int currentSize = sStack.size();
        for (int i = 0; i < currentSize; i++) {
            fStack.push(sStack.pop());
        }
        //just print data
        for (int z = 0; z < fStack.size(); z++) {
            //System.out.println(TAG+"--to fStack--"+fStack.get(z).name);
        }
        return t;
    }

    public static class TestBean{
        public TestBean(String name) {
            this.name = name;
        }

        String name;
    }


    public static void test(){
        StackToQueue s = new StackToQueue();
        for (int i = 0; i < 4; i++) {
            s.insert(new TestBean(TAG+"index="+i));
        }
        System.out.println(TAG+"======check sStack====");
        for (int i = 0; i < 4; i++) {
            System.out.println(TAG+"=="+s.fStack.get(i).name);
        }
        for (int i = 0; i < 4; i++) {
            System.out.println(TAG+"======delete====");
            System.out.println(TAG+"=s.delete().name="+s.delete().name);
            for (int z = 0; z < s.fStack.size(); z++) {
                System.out.println(TAG+"=="+s.fStack.get(z).name);
            }
        }

    }
}
