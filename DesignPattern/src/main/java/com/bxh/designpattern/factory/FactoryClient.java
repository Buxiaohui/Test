package com.bxh.designpattern.factory;

import android.util.Log;

/**
 * Created by buxiaohui on 6/19/17.
 * 工厂模式
 */

public class FactoryClient {
   public static final String TAG = "FactoryClient";

   public static void test(){
       Product productA = new Product(new Factory<Integer>() {
           @Override
           public Integer buildProduct() {
               return new Integer(10101);
           }
       });

       Product productB = new Product(new Factory<String>() {
           @Override
           public String buildProduct() {
               return "String";
           }
       });

       Log.i(TAG,"productA.t="+productA.t);
       Log.i(TAG,"productB.t="+productB.t);
   }
}
