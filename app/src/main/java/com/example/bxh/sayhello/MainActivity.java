package com.example.bxh.sayhello;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //testClassInit();
        //testNestedInit();
        //testAlgorithms();
        //test();
        //TestAlgorithms.testPrintNodeListFromTail2Head();
        //Tree.test();
        //StackToQueue.test();
        //TestAlgorithms.string2String();
        testFibonacci();

    }
    private void testFibonacci(){
        for (int i = 0; i < 10; i++) {
            System.out.println("fibonacci i="+i+"--value="+TestAlgorithms.fibonacci(i));
            System.out.println("fibonacci02 i="+i+"--value="+TestAlgorithms.fibonacci02(i));
        }

    }

    private void testClassInit() {
        System.out.println("classTest -----我是start分割线----");
        new BaseClass();
        System.out.println("classTest -----我是mid分割线----");
        new ChildClass();
        System.out.println("classTest -----我是end分割线----");
    }


    private void testNestedInit() {
        System.out.println("classTest -----我是start分割线----");
        new EnclosingClass();
        System.out.println("classTest -----我是分割线----");
        new EnclosingClass.NestedClass04();
        System.out.println("classTest -----我是分割线----");
        //new EnclosingClass.NestedClass03();


    }

    private void testAlgorithms() {
        double result = TestAlgorithms.test02(3);
        System.out.println("TestAlgorithms-----result=" + result);
    }

    private void test() {
        int[][] array = new int[30][40];
        array[0][0] = 0;
        for (int i = 0; i < array.length; i++) {
            int start = array[0][0]+i;
            for (int z = 0; z < array[0].length; z++) {
                array[i][z] = start + z;
                String out = array[i][z]<10?array[i][z]+" ,":array[i][z]+",";
                System.out.print(out);
                if(z==array[0].length-1){
                    System.out.println();
                }
            }
        }
        boolean x0 = TestAlgorithms.isInside01(37, array);
        boolean x1 = TestAlgorithms.isInside02(37, array);
    }
}
