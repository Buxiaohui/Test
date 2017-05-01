package com.example.bxh.sayhello.sometest;

import android.view.View;

/**
 * Created by bxh on 3/22/17.
 */

public class ExceptionTest {
    public void test3() throws Exception {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance a) {
                System.out.println("BBBBBBB--Caught Annoyance");
                System.out.println("BBBBBB--"+( a instanceof Sneeze));
                throw a;
            }
        } catch (Sneeze s) {
            System.out.println("BBBBBBB--Caught Sneeze");
            return;
        } finally {
            View v;
            System.out.println("BBBBBBB--Hello World!");
        }
    }
    public void test4(){
         String str = "学java";
         String str1 = "java";
         String str2 = "学";
          System.out.println("BBBBBBB111111"+str.getBytes().length);
          System.out.println("BBBBBBB111111"+str1.getBytes().length);
          System.out.println("BBBBBBB111111"+str2.getBytes().length);
          System.out.println("BBBBBBB111111");
//        new Sneeze();
//        int x = 3;
//        int y = 1;
//        if(x == y)
//            System.out.println("BBBBBBB111111");
//        else
//            System.out.println("BBBBBBB222222");
    }
    class Annoyance extends Exception {
//        public Annoyance(int x) {
//            System.out.println("BBBBBBB00000");
//        }
        public Annoyance() {
            Object l;
            System.out.println("BBBBBBB111111");
        }

    }

    class Sneeze extends Annoyance {
        public Sneeze() {
            System.out.println("BBBBBBB222222");
        }

//        public Sneeze(int x){
//            System.out.println("BBBBBBB33333");
//        }
    }


}
