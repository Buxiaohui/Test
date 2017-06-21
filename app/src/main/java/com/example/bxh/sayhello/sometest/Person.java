package com.example.bxh.sayhello.sometest;

/**
 * Created by bxh on 2/22/17.
 */

public class Person implements Cloneable {
    //对象的克隆---clone() 创建并返回此对象的一个副本。必须实现Cloneable接口才能实现克隆
    //如果该类的对象被克隆，就必须在该类明确的重写此方法，但是此方法不能直接调用，看实例

    private String name;

    public Person(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {//对象打印时的调用
        return "姓名：" + this.name;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // 具体的克隆操作由父类完成
    }

    public static void testClone() {

        /**
         * 对象的克隆---对象的复制，完整的一个复制对象
         */
        Person p1 = new Person("张三");
        Person p2 = null;
        try {
            p2 = (Person) p1.clone();//向下转型----P2没有被实例化
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p2.setName("李四");
        System.out.println("testClone 原始对象：" + p1);
        System.out.println("testClone 克隆之后的对象：" + p2);

    }
}
