package com.example.bxh.sayhello.genericity;

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
    }


}
