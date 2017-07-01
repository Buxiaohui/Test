package com.example.bxh.sayhello.genericity;

import android.app.Activity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by buxiaohui on 6/19/17.
 */

public class GenericityTestClient {
    /**
     * <T extends Genericity> 是对Ｔ的约束：Ｔ必须是Genericity的子类
     */
    public static <T extends Genericity> T getGenericity1(T t) {
        return t;
    }

    /**
     * <T> 是对Ｔ的约束：此处是没有约束
     */
    public static <T> T getGenericity2(T t) {
        return t;
    }

    public static void getGenericityTest() {
        GenericityChildA genericityChildA = getGenericity1(new GenericityChildA());
        GenericityChildB genericityChildB = getGenericity2(new GenericityChildB());
        Integer x = getGenericity2(new Integer(1));

        /**************List<? super T>**************/
        List<Genericity> list1 = new ArrayList<>();
        list1.add(new Genericity("Genericity 11111"));
        list1.add(new Genericity("Genericity 22222"));

        //element是GenericityChildA或GenericityChildA的父类
        GenericityTestA<GenericityChildA> genericityTest = new GenericityTestA<GenericityChildA>();
        genericityTest.setList1(list1);

        /**************List<? extends T>**************/
        List<GenericityChildA> list2 = new ArrayList<>();
        list2.add(new GenericityChildA("GenericityChildA 11111"));
        list2.add(new GenericityChildA("GenericityChildA 22222"));
        genericityTest.setList2(list2);



        /**************List<?>**************/
        List list3 = new ArrayList();
        list3.add(1);
        list3.add("string");
        genericityTest.setList3(list3);
        //finally print
        genericityTest.printList();

        //List<?> list3 = new ArrayList();
        //list3.add(11);   //error
        //list3.add("string11"); //error

        /************************************/
        testListGenericity();
    }
    /**
     *  List<String> list = new ArrayList<String>(); 中如何存放一个activity对象
     * */
    public static void testListGenericity() {
        List<String> list = new ArrayList<String>();
        list.add("0000000");
        list.add("1111111");
        list.add("2222222");
        Activity activityA = new Activity();
        Activity activityB = new Activity();
        Activity activityC = new Activity();
        ((List)list).add(activityA);
        try {
            Method methodAdd1 = list.getClass().getMethod("add",Object.class);
            methodAdd1.invoke(list,activityB);
            //Method methodAdd2 = list.getClass().getMethod("add",Class<?>);
            //methodAdd2.invoke(list,activityC);

            for (int i = 0; i < list.size(); i++) {
                //System.out.printf("testListGenericity->"+list.get(i));
            }
        }catch (Exception e){

        }

    }

}
