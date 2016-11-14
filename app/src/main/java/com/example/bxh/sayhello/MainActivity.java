package com.example.bxh.sayhello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //testClassInit();
        //testNestedInit();
        testAlgorithms();
    }


    private void testClassInit() {
        System.out.println("classTest -----我是start分割线----");
        new BaseClass();
        System.out.println("classTest -----我是mid分割线----");
        new ChildClass();
        System.out.println("classTest -----我是end分割线----");
    }


    private void testNestedInit(){
        System.out.println("classTest -----我是start分割线----");
        new EnclosingClass();
        System.out.println("classTest -----我是分割线----");
        new EnclosingClass.NestedClass04();
        System.out.println("classTest -----我是分割线----");
        //new EnclosingClass.NestedClass03();


    }

    private void testAlgorithms(){
        double result = TestAlgorithms.test02(3);
        System.out.println("TestAlgorithms-----result="+result);
    }
}
