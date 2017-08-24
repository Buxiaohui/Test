package com.example.bxh.sayhello.enumtest;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by buxiaohui on 17-8-22.
 */

public class EnumTest1 {
    private static final String TAG = "EnumTest1";
    public int type;

    public static void test() {
        EnumTest1 enumTest = new EnumTest1();
    }

    public enum Phase {
        SOLID, LIQUID, GAS;
        public enum Transition{
            MELT(SOLID,LIQUID), FREEZE(LIQUID,SOLID),
            BOIL(LIQUID,GAS), CONDENSE(GAS,LIQUID),
            SUBLIME(SOLID,GAS), DEPOSIT(GAS,SOLID);

            private final Phase src;
            private final Phase dst;
            Transition(Phase src,Phase dst){
                this.src = src;
                this.dst = dst;
            }
            private static final Map<Phase,Map<Phase,Transition>> m = new EnumMap<Phase,Map<Phase,Transition>>(Phase.class);
            static {
                for (Phase p : Phase.values()) {
                     //m.put(p,)
                }
            }
        }
    }

}
